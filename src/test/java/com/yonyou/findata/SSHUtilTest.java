package com.yonyou.findata;

import com.yonyou.findata.ssh.SSHUtil;
import org.junit.Test;

/**
 * @author: pizhihui
 * @datae: 2017-07-12
 */
public class SSHUtilTest {

    @Test
    public void testExecuteCmd() {

        SSHUtil.execute("netstat -nltp|grep redis-server", "192.168.197.128");
    }

    @Test
    public void testScpLocalToRemote() {
        SSHUtil.scpLocalToRemote("192.168.197.128",22,"root", "root",
                "/tmp/kvm/ks_oracle12c.cfg",
                "/kvm/conf/",
                "0644");
    }

}
