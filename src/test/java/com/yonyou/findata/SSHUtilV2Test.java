package com.yonyou.findata;

import com.yonyou.findata.exception.SSHException;
import com.yonyou.findata.ssh.SSHFunction;
import com.yonyou.findata.ssh.SSHUtil;
import com.yonyou.findata.ssh.SSHUtilV2;
import com.yonyou.findata.ssh.SSHUtilV2.SSHSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author: pizhihui
 * @datae: 2017-07-12
 */
public class SSHUtilV2Test {

    @Test
    public void testExecuteMultiCmd() throws SSHException {

        //SSHUtil.execute("netstat -nltp|grep redis-server", "192.168.197.128");
        //> /kvm/logs/test.log 2>&1 &
        //SSHUtil.execute("virsh list --all ", "192.168.197.178");

        // java 7 and earlier
       /* SSHUtilV2.execute("192.168.197.178", 22, "root", "root", new SSHUtilV2.SSHCallBack() {
            @Override
            public void call(SSHUtilV2.SSHSession session) {
                session.execute("virsh list --all ", new SSHUtilV2.LineProcessor() {
                    @Override
                    public void process(String line, int lineNbr) {
                        System.out.println( lineNbr + " ..... " + line);
                    }
                });
                session.execute("netstat -nltp", new SSHUtilV2.LineProcessor() {
                    @Override
                    public void process(String line, int lineNbr) {
                        System.out.println(lineNbr + "...." + line);
                    }
                });
                session.execute("who", new SSHUtilV2.LineProcessor() {
                    @Override
                    public void process(String line, int lineNbr) {
                        System.out.println(lineNbr + "...." + line);
                    }
                });
            }
        });*/

       // java 8
        SSHUtilV2.Result res = SSHUtilV2.execute("192.168.197.178", 22, "root", "root", (SSHSession session) -> {
            session.executeCmd("virsh list --all", (line, lineNbr) -> {
                try {
                    System.out.println("lineNr" + "..." + line);

                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            SSHUtilV2.Result result = session.executeCmd("netstat -nltp", null);
            System.out.println(result.getMessage());
            session.executeCmd("who", SSHFunction.defaultLineProcessor);
            session.executeCmd(" ps -ef | grep java", SSHFunction.defaultLineProcessor);
            return new SSHUtilV2.Result(true, "sucess");
        });
        System.out.println("res....." + res.getMessage());
        // 花生壳内网穿透不行
//        SSHUtilV2.execute("17721q14j0.51mypc.cn", 29986, "root", "root", (session) -> {
//            session.execute("netstat -nltp", SSHFunction.defaultLineProcessor);
//        });

    }

    @Test
    public void testDirectCmd() {
        SSHUtilV2.Result res = SSHUtilV2.execute("virsh list --all", "192.168.197.178");
        System.out.println("msg: " + res.getMessage());
        System.out.println("error: " + res.getException());
//        String[] cmds = new String[]{};
//        cmds[0] = "netstatt -nltp";
//        cmds[1] = "who";
//        cmds[2] = "ps -ef | grep java";
//        SSHUtilV2.Result[] ress = SSHUtilV2.execute(cmds, "192.168.197.178", 22, "root", "root");
//        Arrays.asList(ress).forEach(System.out::println);
    }


    @Test
    public void testScpLocalToRemote() {
        SSHUtil.scpLocalToRemote("192.168.197.128",22,"root", "root",
                "/tmp/kvm/ks_oracle12c.cfg",
                "/kvm/conf/",
                "0644");
    }

}
