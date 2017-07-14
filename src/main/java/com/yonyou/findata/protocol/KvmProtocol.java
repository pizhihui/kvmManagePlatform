package com.yonyou.findata.protocol;

import com.yonyou.findata.util.ConstUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: pizhihui
 * @datae: 2017-07-14
 */
public class KvmProtocol {

    private static final Logger logger = LoggerFactory.getLogger(KvmProtocol.class);

    public static final String RUNN_SHELL = "virt-install";

    public static final String CONFIG_FILE = "ks_%s.cfg";


/*
virt-install \
--name kvm-oracle12c \
--virt-type kvm \
--ram 16384 \
--vcpus 4 \
--disk /kvmdir/kvm-oracle12c.img,format=qcow2,size=200 \
--location /opt/CentOS-7-x86_64-DVD-1611.iso  \
--network bridge=br0 \
--os-type=linux \
--os-variant=rhel7 \
--nographics \
--initrd-inject=/root/ks_7_autoinstall.cfg \
-x "ks=file:/ks_7_autoinstall.cfg console=ttyS0"
 */

    /**
     * 获取kvm安装命令
     * @param ram  内存,单位
     * @param vcpus cpu核数
     * @param name kvm名字
     * @return
     */
    public static String getInstallRunShell(int ram, int vcpus, String name) {
        StringBuilder cmd = new StringBuilder();
        cmd.append(RUNN_SHELL + " \\\n");
        cmd.append("--name " + name + " \\\n");
        cmd.append("--virt-type kvm \\\n");
        cmd.append("--ram " + String.valueOf(ram * 1024) + " \\\n");
        cmd.append("--vcpus " + String.valueOf(vcpus) + " \\\n");
        cmd.append("--disk " + MachineProtocol.DISK_DIR + "kvm-" + name + ".img,format=qcow2,size=200 \\\n");
        cmd.append("--location /opt/CentOS-7-x86_64-DVD-1611.iso  \\\n");
        cmd.append("--network bridge=br0 \\\n");
        cmd.append("--os-type=linux \\\n");
        cmd.append("--nographics \\\n");
        cmd.append("--initrd-inject=" + MachineProtocol.CONF_DIR + getConfigFile(name) + " \\\n");
        cmd.append("-x 'ks=file:/" + getConfigFile(name) + " console=ttyS0'");
        //logger.info("Kvm install cmd is : {}" + cmd.toString());
        return cmd.toString();
    }

    /**
     * 生成安装配置文件的名称
     * @param name
     * @return
     */
    public static String getConfigFile(String name) {
        return String.format(CONFIG_FILE, name);
    }


    public static void main(String[] args) {
        String oracle12c = getInstallRunShell(4, 4, "oracle12c");
        System.out.println(oracle12c);
    }

}
