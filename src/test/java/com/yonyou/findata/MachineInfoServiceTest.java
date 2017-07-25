package com.yonyou.findata;

import com.yonyou.findata.model.MachineInfo;
import com.yonyou.findata.service.MachineInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: pizhihui
 * @datae: 2017-07-12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class MachineInfoServiceTest {

    @Autowired
    private MachineInfoService machineInfoService;

    @Test
    public void testTransaction() {
        MachineInfo machineInfo = new MachineInfo();
        machineInfo.setCpu(4);
        machineInfo.setName("haha");
        machineInfo.setIp("192.168.197.133");
        machineInfo.setHostIp("192.168.197.2");
        machineInfo.setMem(4);
        machineInfo.setType(1);
        machineInfo.setDescription("测试数据");
        machineInfoService.addMachine(machineInfo);
    }

}
