package com.yonyou.findata.service;

import com.yonyou.findata.model.MachineInfo;

import java.util.List;

/**
 * @author: pizhihui
 * @datae: 2017-07-14
 */
public interface VirtMachineInstallService {

    /**
     * 安装虚拟机
     * @param info 虚拟机配置信息
     */
    void installVirtMachine(MachineInfo info);


}
