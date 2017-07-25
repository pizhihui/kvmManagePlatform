package com.yonyou.findata;

import com.yonyou.findata.model.MachineInfo;
import com.yonyou.findata.service.MachineInfoService;
import com.yonyou.findata.service.VirtMachineOpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: pizhihui
 * @datae: 2017-07-14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class VirtMachineInstallOpTest {

    @Autowired
    private MachineInfoService infoService;
    @Autowired
    private VirtMachineOpService opService;

    @Test
    public void testInstall() {
        MachineInfo info = new MachineInfo();
        info.setIp("192.168.197.100");
        info.setName("oracle1112");
        info.setMem(2);
        info.setHostIp("192.168.197.178");
        info.setCpu(2);
        info.setDescription("这是测试用例中的");
        opService.installVirtMachine(info);
    }

    @Test
    public void testListMachineByHostIp() {
        List<MachineInfo> virtMachine = infoService.getVirtMachine("192.168.197.178");
        virtMachine.forEach(v -> System.out.println(v.toString()));
    }


    @Test
    public void testDeleteVirtMachine() {
        opService.deleteVirtMachine(28);

    }

    @Test
    public void testCheckVirtMachineState() {
        String s = opService.checkVirtMachineStatus(29);
        System.out.println("....." + s);
    }




}
