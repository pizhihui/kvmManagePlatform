package com.yonyou.findata.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "machine_info")
public class MachineInfo implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 机器的名字
     */
    private String name;

    /**
     * 机器ip
     */
    private String ip;

    /**
     * 所属物理机ip,物理机为自身ip
     */
    @Column(name = "host_ip")
    private String hostIp;

    /**
     * cpu核数
     */
    private Integer cpu;

    /**
     * 内存,单位G
     */
    private Integer mem;

    /**
     * 虚拟机运行状态
     */
    private String state;

    /**
     * 机器类别,0物理机,1虚拟机
     */
    private Integer type;

    /**
     * 描述信息
     */
    private String description;

    private static final long serialVersionUID = 1L;

    public MachineInfo(Long id, String name, String ip, String hostIp, Integer cpu, Integer mem, String state, Integer type, String description) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.hostIp = hostIp;
        this.cpu = cpu;
        this.mem = mem;
        this.state = state;
        this.type = type;
        this.description = description;
    }

    public MachineInfo() {
        super();
    }

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取机器的名字
     *
     * @return name - 机器的名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置机器的名字
     *
     * @param name 机器的名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取机器ip
     *
     * @return ip - 机器ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置机器ip
     *
     * @param ip 机器ip
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取所属物理机ip,物理机为自身ip
     *
     * @return host_ip - 所属物理机ip,物理机为自身ip
     */
    public String getHostIp() {
        return hostIp;
    }

    /**
     * 设置所属物理机ip,物理机为自身ip
     *
     * @param hostIp 所属物理机ip,物理机为自身ip
     */
    public void setHostIp(String hostIp) {
        this.hostIp = hostIp == null ? null : hostIp.trim();
    }

    /**
     * 获取cpu核数
     *
     * @return cpu - cpu核数
     */
    public Integer getCpu() {
        return cpu;
    }

    /**
     * 设置cpu核数
     *
     * @param cpu cpu核数
     */
    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    /**
     * 获取内存,单位G
     *
     * @return mem - 内存,单位G
     */
    public Integer getMem() {
        return mem;
    }

    /**
     * 设置内存,单位G
     *
     * @param mem 内存,单位G
     */
    public void setMem(Integer mem) {
        this.mem = mem;
    }

    /**
     * 获取虚拟机运行状态
     *
     * @return state - 虚拟机运行状态
     */
    public String getState() {
        return state;
    }

    /**
     * 设置虚拟机运行状态
     *
     * @param state 虚拟机运行状态
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 获取机器类别,0物理机,1虚拟机
     *
     * @return type - 机器类别,0物理机,1虚拟机
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置机器类别,0物理机,1虚拟机
     *
     * @param type 机器类别,0物理机,1虚拟机
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取描述信息
     *
     * @return description - 描述信息
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述信息
     *
     * @param description 描述信息
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
        sb.append(", state=").append(state);
        sb.append(", type=").append(type);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}