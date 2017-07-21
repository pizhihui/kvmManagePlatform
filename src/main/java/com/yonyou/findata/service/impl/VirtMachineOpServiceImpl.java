package com.yonyou.findata.service.impl;

import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.yonyou.findata.dao.MachineInfoMapper;
import com.yonyou.findata.model.MachineInfo;
import com.yonyou.findata.model.MachineInfoExample;
import com.yonyou.findata.protocol.KvmProtocol;
import com.yonyou.findata.protocol.MachineProtocol;
import com.yonyou.findata.service.KvmConfigService;
import com.yonyou.findata.service.VirtMachineOpService;
import com.yonyou.findata.ssh.SSHUtil;
import com.yonyou.findata.util.FileUtil;
import com.yonyou.findata.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author: pizhihui
 * @datae: 2017-07-14
 */
@Service
@Transactional
public class VirtMachineOpServiceImpl implements VirtMachineOpService {

    private static final Logger logger = LoggerFactory.getLogger(VirtMachineOpServiceImpl.class);

    @Autowired
    private KvmConfigService kvmConfigService;
    @Autowired
    private MachineInfoMapper machineInfoMapper;

    @Override
    public void installVirtMachine(MachineInfo info) {
        // 获取配置项
        List<String> configs = kvmConfigService.getAllConfigs(info.getIp());
        createRemoteFile(info.getHostIp(), info.getName(), configs);
        // 执行安装命令
        String installCmd = KvmProtocol.getInstallRunShell(info.getMem(), info.getCpu(), info.getName());
        logger.info("start install with cmd : {}", installCmd);
        SSHUtil.execute(installCmd, info.getHostIp());
        // 存储数据库
        info.setState("running"); // 默认状态
        info.setType(1);
        machineInfoMapper.insert(info);
    }

    @Override
    public void shutDownVirtMachine(long machineId) {

    }

    @Override
    public void startVirtMachine(long machineId) {

    }

    @Override
    public void deleteVirtMachine(long machineId) {
        try {
            MachineInfo machineInfo = machineInfoMapper.selectByPrimaryKey(machineId);
            SSHUtil.execute(String.format(KvmProtocol.SHUTDOWN_SHELL, machineInfo.getName()), machineInfo.getHostIp(), new SSHUtil.DeleteSSHCallBack());
            SSHUtil.execute(String.format(KvmProtocol.DESTROY_SHELL, machineInfo.getName()), machineInfo.getHostIp(), new SSHUtil.DeleteSSHCallBack());
            TimeUnit.SECONDS.sleep(5);
            SSHUtil.execute(String.format(KvmProtocol.UNDEFINE_SHELL, machineInfo.getName()), machineInfo.getHostIp(), new SSHUtil.DeleteSSHCallBack());
            SSHUtil.execute("rm -rf " + MachineProtocol.DISK_DIR + "kvm-" + machineInfo.getName() + ".img", machineInfo.getHostIp(), new SSHUtil.DeleteSSHCallBack());
            machineInfoMapper.deleteByPrimaryKey(machineId);
        } catch (InterruptedException e) {
            logger.error("delete virt machine error: ", e);
        }

    }

    @Override
    public String checkVirtMachineStatus(long machineId) {
        final String[] status = {"shut"};
        final MachineInfo machineInfo = machineInfoMapper.selectByPrimaryKey(machineId);
        SSHUtil.execute(KvmProtocol.LIST_SHELL, machineInfo.getHostIp(), new SSHUtil.SSHCallBack() {
            @Override
            public void call(Session session, String cmd) {
                try {
                    //session.startShell();
                    session.execCommand(cmd); // 执行命令
                    InputStream stdout = new StreamGobbler(session.getStdout());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdout));
                    String line = null;
                    int lineNbr = 1;
                    while ((line = bufferedReader.readLine()) != null) {
                        //System.out.println(lineNbr + " : " + line); // 打印返回的内容
                        if (lineNbr >= 3 ) { //重启完毕后,已经完成
                            if (!StringUtil.isBlank(line)) {
                                String[] machineStats = line.split("\\s+");
                                String name = machineStats[2];//虚拟机名字
                                String state = machineStats[3];
                                //System.out.println("name : " + machineStats[2]);  // name
                                //System.out.println("State :" + machineStats[3] );
                                if (machineInfo.getName().equalsIgnoreCase(name)) {
                                    status[0] = state;
                                }
                            }
                        }
                        lineNbr++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return status[0];
    }


    // 创建配置文件并拷贝到需要安装虚拟机的物理机的磁盘上
    private void createRemoteFile(String host, String name, List<String> content) {
        // 判断本地目录
        FileUtil.judDirExists(MachineProtocol.TEMP_DIR);
        // 临时存放配置文件目录
        String tempPathFile = MachineProtocol.TEMP_DIR + String.format(KvmProtocol.CONFIG_FILE, name);
        Path path = Paths.get(MachineProtocol.TEMP_DIR, String.format(KvmProtocol.CONFIG_FILE, name));
        try {
            BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"));
            try {
                for (String line : content) {
                    writer.write(line);
                    writer.newLine();
                }
            } finally {
                if (null != writer) {
                    writer.close();
                }
            }
        } catch (IOException e) {
            logger.error("write kvm install config file error, host: {}, name: {}, content: {} e", host, name, content, e);
        }

        // 将配置文件拷贝到远程机器上
        logger.info("cp local file to remote machine");
        SSHUtil.scpLocalToRemote(host, tempPathFile, MachineProtocol.CONF_DIR);
    }


}
