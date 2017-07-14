package com.yonyou.findata.service.impl;

import com.yonyou.findata.dao.MachineInfoMapper;
import com.yonyou.findata.exception.CommonException;
import com.yonyou.findata.model.MachineInfo;
import com.yonyou.findata.service.MachineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: pizhihui
 * @datae: 2017-07-12
 */
@Service
@Transactional
public class MachineInfoServiceImpl implements MachineInfoService {

    @Autowired
    private MachineInfoMapper machineInfoMapper;

    @Override
    public void addMachine(MachineInfo machineInfo) throws CommonException {
        machineInfoMapper.insert(machineInfo);
    }

    @Override
    public MachineInfo getMachineInfoById(Long Id) throws CommonException {
        return machineInfoMapper.selectByPrimaryKey(Id);
    }
}
