package com.yonyou.findata.service;

import com.yonyou.findata.exception.CommonException;
import com.yonyou.findata.model.MachineInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: pizhihui
 * @datae: 2017-07-12
 */
public interface MachineInfoService {

    /**
     * 添加物理机器
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

    /**
     * 获取所在物理机上的虚拟机信息
     * @param hostIp 物理机ip
     * @return
     */
    List<MachineInfo> getVirtMachine(String hostIp);

    /**
     * 获取所有物理机上的虚拟机
     * @return
     */
    List<MachineInfo> getAllVirtMachines();

    /**
     * 获取所有的物理机
     * @return
     */
    List<MachineInfo> getAllHostMachines();
}
