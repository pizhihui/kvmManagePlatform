package com.yonyou.findata.service.impl;

import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.yonyou.findata.dao.MachineInfoMapper;
import com.yonyou.findata.exception.CommonException;
import com.yonyou.findata.model.MachineInfo;
import com.yonyou.findata.protocol.KvmProtocol;
import com.yonyou.findata.service.MachineInfoService;
import com.yonyou.findata.ssh.SSHUtil;
import com.yonyou.findata.util.NetUtil;
import com.yonyou.findata.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: pizhihui
 * @datae: 2017-07-12
 */
@Service
@Transactional
public class MachineInfoServiceImpl implements MachineInfoService {

    private static final Logger logger = LoggerFactory.getLogger(MachineInfoServiceImpl.class);


    @Autowired
    private MachineInfoMapper machineInfoMapper;

    @Override
    public void addMachine(MachineInfo machineInfo) throws CommonException {
        machineInfo.setType(0); // 物理机
        machineInfo.setState(null); // 设置状态为null
        machineInfoMapper.insert(machineInfo);
    }

    @Override
    public MachineInfo getMachineInfoById(Long Id) throws CommonException {
        return machineInfoMapper.selectByPrimaryKey(Id);
    }


    @Override
    public List<MachineInfo> getVirtMachine(String hostIp) {

        // 获取当前物理机上的所有虚拟机
        MachineInfo info = new MachineInfo();
        info.setType(1);
        info.setHostIp(hostIp);
        List<MachineInfo> machineInfos = machineInfoMapper.select(info);

        // 获取状态
        SSHUtil.execute(KvmProtocol.LIST_SHELL, hostIp, new SSHUtil.SSHCallBack() {
            @Override
            public void call(Session session, String cmd) {
                try {
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
                                String state = machineStats[3];//虚拟机运行状态
                                System.out.println("name : " + machineStats[2]);  // name
                                System.out.println("State :" + machineStats[3] );
                                Optional<MachineInfo> machineInfoOp = machineInfos.stream().filter(v -> v.getName().equalsIgnoreCase(name)).findFirst();
                                if (machineInfoOp.isPresent()) {
                                    machineInfoOp.get().setState(state);
                                }
                            }
                        }
                        lineNbr++;
                    }
                } catch (IOException e) {
                    logger.error("execute cmd {} error", cmd, e);
                }

            }
        });

        return machineInfos;
    }

    @Override
    public List<MachineInfo> getAllVirtMachines() {



        return null;
    }

    @Override
    public List<MachineInfo> getAllHostMachines() {
        // 获取当前物理机上的所有虚拟机
        MachineInfo info = new MachineInfo();
        info.setType(0);
        List<MachineInfo> machineInfos = machineInfoMapper.select(info);
        // 校验物理机是ping通的
        List<MachineInfo> infos = machineInfos.stream().filter(v -> NetUtil.isReachable(v.getIp())).collect(Collectors.toList());
        return infos;
    }
}
