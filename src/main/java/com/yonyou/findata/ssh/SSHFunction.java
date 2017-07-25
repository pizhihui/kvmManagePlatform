package com.yonyou.findata.ssh;

/**
 * @author: pizhihui
 * @datae: 2017-07-24
 * @version 0.2
 */
public class SSHFunction {

    /**
     * 测试的行处理器: 打印行号和行内容
     */
    public static SSHUtilV2.LineProcessor defaultTestProcessor  =
            (line, lineNbr) -> System.out.println( lineNbr + " ..... " + line);

    /**
     * 默认的行处理器: 获得没行内容然后String返回
     */
    public static SSHUtilV2.LineProcessor defaultLineProcessor  = (line, lineNbr) -> {
        StringBuilder sb = new StringBuilder();
        if (lineNbr > 1) {
            sb.append(System.lineSeparator());
        }
        sb.append(line);
    };


}
