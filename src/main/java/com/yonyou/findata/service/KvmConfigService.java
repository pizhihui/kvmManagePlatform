package com.yonyou.findata.service;

import java.util.List;

/**
 * @author: pizhihui
 * @datae: 2017-07-14
 */
public interface KvmConfigService {


    /**
     * 获取所有的kvm安装配置
     * @return
     */
    List<String> getAllConfigs(String ip);


}
