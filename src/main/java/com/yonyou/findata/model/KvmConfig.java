package com.yonyou.findata.model;

import java.io.Serializable;

public class KvmConfig implements Serializable {
    private Long id;

    private String configKey;

    private String configValue;

    private String info;

    private Integer osType;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey == null ? null : configKey.trim();
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Integer getOsType() {
        return osType;
    }

    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    public Integer getStatus() {
        return status;
    }

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