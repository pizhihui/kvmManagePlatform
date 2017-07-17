package com.yonyou.findata.service.impl;

import com.yonyou.findata.model.MachineInfo;
import com.yonyou.findata.protocol.KvmProtocol;
import com.yonyou.findata.protocol.MachineProtocol;
import com.yonyou.findata.service.KvmConfigService;
import com.yonyou.findata.service.VirtMachineInstallService;
import com.yonyou.findata.ssh.SSHUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author: pizhihui
 * @datae: 2017-07-14
 */
@Service
@Transactional
public class VirtMachineInstallServiceImpl implements VirtMachineInstallService {

    private static final Logger logger = LoggerFactory.getLogger(VirtMachineInstallServiceImpl.class);

    @Autowired
    private KvmConfigService kvmConfigService;

    @Override
    public void installVirtMachine(MachineInfo info) {
        // 生成配置文件
        List<String> configs = kvmConfigService.getAllConfigs(info.getIp());
        createRemoteFile(info.getIp(), info.getName(), configs);
        // 执行安装命令
        String installCmd = KvmProtocol.getInstallRunShell(info.getMem(), info.getCpu(), info.getName());
        SSHUtil.execute(installCmd, info.getHostIp());
    }


    // 创建配置文件并拷贝到需要安装虚拟机的物理机的磁盘上
    private void createRemoteFile(String host, String name, List<String> content) {
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
        SSHUtil.scpLocalToRemote(host, tempPathFile, MachineProtocol.CONF_DIR);
    }


}
