package com.yonyou.findata.service;

import com.yonyou.findata.exception.CommonException;
import com.yonyou.findata.model.MachineInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: pizhihui
 * @datae: 2017-07-12
 */
public interface MachineInfoService {

    /**
     * 添加机器
     * @param machineInfo 机器
     * @throws CommonException 异常
     */
    void addMachine(MachineInfo machineInfo) throws CommonException;

    /**
     * 通过id获取机器信息
     * @param Id
     * @return
     * @throws CommonException
     */
    MachineInfo getMachineInfoById(Long Id) throws CommonException;

}
