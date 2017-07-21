package com.yonyou.findata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author: pizhihui
 * @datae: 2017-07-21
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void judDirExists(String dir) {
        File file = new File(dir);
        judDirExists(file);
    }

    public static void judDirExists(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                logger.info("dir exists");
            } else {
                logger.info("the same name file exists, can not create dir");
            }
        } else {
            file.mkdir();
            logger.info("dir not exists, create it....");
        }
    }


}
