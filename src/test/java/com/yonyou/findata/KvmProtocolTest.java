package com.yonyou.findata;

import com.yonyou.findata.protocol.KvmProtocol;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: pizhihui
 * @datae: 2017-07-17
 */
public class KvmProtocolTest {

    public static final Logger logger = LoggerFactory.getLogger(KvmProtocolTest.class);


    @Test
    public void testKvmCmdEcho() {
        logger.info(KvmProtocol.getInstallRunShell(16, 4, "oracle12c"));
    }

}
