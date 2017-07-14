package com.yonyou.findata.model;

import java.io.Serializable;

public class MachineInfo implements Serializable {
    private Long id;

    private String name;

    private String ip;

    private String hostIp;

    private Integer cpu;

    private Integer mem;

    private Integer type;

    private String desc;

    private static final long serialVersionUID = 1L;

    public MachineInfo(Long id, String name, String ip, String hostIp, Integer cpu, Integer mem, Integer type, String desc) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.hostIp = hostIp;
        this.cpu = cpu;
        this.mem = mem;
        this.type = type;
        this.desc = desc;
    }

    public MachineInfo() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp == null ? null : hostIp.trim();
    }

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public Integer getMem() {
        return mem;
    }

    public void setMem(Integer mem) {
        this.mem = mem;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", ip=").append(ip);
        sb.append(", hostIp=").append(hostIp);
        sb.append(", cpu=").append(cpu);
        sb.append(", mem=").append(mem);
        sb.append(", type=").append(type);
        sb.append(", desc=").append(desc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}