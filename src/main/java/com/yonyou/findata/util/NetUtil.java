package com.yonyou.findata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author: pizhihui
 * @datae: 2017-07-18
 */
public class NetUtil {

    private static final Logger logger = LoggerFactory.getLogger(NetUtil.class);
    private static final int timeout = 3000;

    public static boolean isReachable(String ip) {
        boolean status =false;
        if (!ip.trim().isEmpty() && null != ip) {
            try {
                boolean reachable = InetAddress.getByName(ip).isReachable(timeout);
                status = reachable;
            } catch (IOException e) {
                logger.error("ip: {} can not reachable!", ip, e);
            }
        }
        return status;
    }

}
