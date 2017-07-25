package com.yonyou.findata;

import com.yonyou.findata.ssh.SSHUtil;
import com.yonyou.findata.ssh.SSHUtilV2;
import org.junit.Test;

/**
 * @author: pizhihui
 * @datae: 2017-07-12
 */
@Deprecated
public class SSHUtilTest {

    @Test
    public void testExecuteCmd() {

        //SSHUtil.execute("netstat -nltp|grep redis-server", "192.168.197.128");
        //> /kvm/logs/test.log 2>&1 &
        SSHUtil.execute("virsh list --all ", "192.168.197.178");



    }

    @Test
    public void testScpLocalToRemote() {
        SSHUtil.scpLocalToRemote("192.168.197.128",22,"root", "root",
                "/tmp/kvm/ks_oracle12c.cfg",
                "/kvm/conf/",
                "0644");
    }

}
