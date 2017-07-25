package com.yonyou.findata.service;

import com.yonyou.findata.model.MachineInfo;

import java.util.List;

/**
 * @author: pizhihui
 * @datae: 2017-07-14
 */
public interface VirtMachineOpService {

    /**
     * 安装虚拟机
     * @param info 虚拟机配置信息
     */
    void installVirtMachine(MachineInfo info);

    /**
     * 关闭虚拟机
     * @param machineId
     */
    void shutDownVirtMachine(long machineId);

    /**
     * 启动虚拟机
     * @param machineId
     */
    void startVirtMachine(long machineId);

    /**
     * 删除虚拟机
     * @param machineId
     */
    void deleteVirtMachine(long machineId);

    /**
     * 检查虚拟机状态
     * @param machineId
     */
    String checkVirtMachineStatus(long machineId);


    /**
     * 修改虚拟机配置
     * @param machineId
     * @param machineCpu
     * @param machineMem
     */
    void editVirtMachineSetting(long machineId, int machineCpu, int machineMem);






}
