package com.yonyou.findata.service.impl;

import com.yonyou.findata.dao.KvmConfigMapper;
import com.yonyou.findata.model.KvmConfig;
import com.yonyou.findata.model.KvmConfigExample;
import com.yonyou.findata.service.KvmConfigService;
import com.yonyou.findata.util.ConstUtils;
import com.yonyou.findata.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: pizhihui
 * @datae: 2017-07-14
 */
@Service
@Transactional
public class KvmConfigServiceImpl implements KvmConfigService {

    @Autowired
    private KvmConfigMapper kvmConfigMapper;

    @Override
    public List<String> getAllConfigs(String ip) {
        KvmConfigExample example = new KvmConfigExample();
        example.createCriteria().andStatusEqualTo(1);
        List<KvmConfig> kvmConfigs = kvmConfigMapper.selectByExample(example);
        return handleKvmInstalConfig(ip, kvmConfigs);
    }


    /**
     * 处理从数据库得到的配置
     * @param kvmConfigs
     * @return
     */
    private List<String> handleKvmInstalConfig(String ip, List<KvmConfig> kvmConfigs) {
        List<String> configs = new ArrayList<String>();
        StringBuilder post = new StringBuilder("%post \n");
        StringBuilder packages = new StringBuilder("%packages \n");
        for (KvmConfig config : kvmConfigs) {
            String configKey = config.getConfigKey();
            String configValue = config.getConfigValue();
            //处理没有value的情况
            if (StringUtil.isBlank(configValue)) {
                configs.add(combineKvmKeyValue(configKey, ""));
            } else if ("%post".equalsIgnoreCase(configKey)) {
                post.append(configValue + ConstUtils.NEW_LINE);
            } else if ("%packages".equalsIgnoreCase(configKey)) {
                packages.append(configValue + ConstUtils.NEW_LINE);

            } else if ("network".equalsIgnoreCase(configKey)) {
                configs.add(combineKvmKeyValue(configKey, String.format(configValue, ip)));
            } else {
                configs.add(combineKvmKeyValue(configKey, configValue));

            }
        }
        configs.add(post.append("%end").append(ConstUtils.NEW_LINE).toString());
        configs.add(packages.append("%end").append(ConstUtils.NEW_LINE).toString());
        return configs;
    }


    private String combineKvmKeyValue(String key, String value) {
        return key + ConstUtils.SPACE + value;
    }
}
