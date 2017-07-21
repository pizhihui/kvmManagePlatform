package com.yonyou.findata;

import com.yonyou.findata.dao.MachineInfoMapper;
import com.yonyou.findata.model.MachineInfo;
import com.yonyou.findata.model.MachineInfoExample;
import org.junit.Assert;
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
public class MachineInfoDaoTest {

    @Autowired
    private MachineInfoMapper machineInfoMapper;

    @Test
    public void testInsert() {
        MachineInfo machineInfo = new MachineInfo();
        machineInfo.setCpu(4);
        machineInfo.setHostIp("192.168.197.2");
        machineInfo.setIp("192.168.197.128");
        machineInfo.setMem(4);
        machineInfo.setType(1);
        machineInfo.setDescription("测试数据");
        machineInfoMapper.insert(machineInfo);
    }

    @Test
    public void testQuery() {

        MachineInfo machineInfo = machineInfoMapper.selectByPrimaryKey(3L);
        Assert.assertEquals("4", machineInfo.getMem().toString());
    }

    @Test
    public void testDelete() {
        machineInfoMapper.deleteByPrimaryKey(29L);
    }
}
