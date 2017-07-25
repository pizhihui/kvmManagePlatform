package com.yonyou.findata.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "kvm_config")
public class KvmConfig implements Serializable {
    @Id
    private Long id;

    /**
     * 配置key
     */
    @Column(name = "config_key")
    private String configKey;

    /**
     * 配置value
     */
    @Column(name = "config_value")
    private String configValue;

    /**
     * 属性描述信息
     */
    private String info;

    /**
     * 所属的系统类型 1: all,6:centos6,7centos7
     */
    @Column(name = "os_type")
    private Integer osType;

    /**
     * 状态: 0无效,1有效
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    public KvmConfig(Long id, String configKey, String configValue, String info, Integer osType, Integer status) {
        this.id = id;
        this.configKey = configKey;
        this.configValue = configValue;
        this.info = info;
        this.osType = osType;
        this.status = status;
    }

    public KvmConfig() {
        super();
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取配置key
     *
     * @return config_key - 配置key
     */
    public String getConfigKey() {
        return configKey;
    }

    /**
     * 设置配置key
     *
     * @param configKey 配置key
     */
    public void setConfigKey(String configKey) {
        this.configKey = configKey == null ? null : configKey.trim();
    }

    /**
     * 获取配置value
     *
     * @return config_value - 配置value
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * 设置配置value
     *
     * @param configValue 配置value
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    /**
     * 获取属性描述信息
     *
     * @return info - 属性描述信息
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置属性描述信息
     *
     * @param info 属性描述信息
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * 获取所属的系统类型 1: all,6:centos6,7centos7
     *
     * @return os_type - 所属的系统类型 1: all,6:centos6,7centos7
     */
    public Integer getOsType() {
        return osType;
    }

    /**
     * 设置所属的系统类型 1: all,6:centos6,7centos7
     *
     * @param osType 所属的系统类型 1: all,6:centos6,7centos7
     */
    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    /**
     * 获取状态: 0无效,1有效
     *
     * @return status - 状态: 0无效,1有效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态: 0无效,1有效
     *
     * @param status 状态: 0无效,1有效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", configKey=").append(configKey);
        sb.append(", configValue=").append(configValue);
        sb.append(", info=").append(info);
        sb.append(", osType=").append(osType);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}