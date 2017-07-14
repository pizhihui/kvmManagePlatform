package com.yonyou.findata;

import com.yonyou.findata.model.MachineInfo;
import com.yonyou.findata.service.VirtMachineInstallService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: pizhihui
 * @datae: 2017-07-14
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class VirtMachineInstallServiceTest {

    @Autowired
    private VirtMachineInstallService installService;

    @Test
    public void testInstall() {
        MachineInfo info = new MachineInfo();
        info.setIp("10.10.4.210");
        installService.installVirtMachine(info, "10.10.4.230");
    }


}
