package com.yonyou.findata.ssh;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.yonyou.findata.util.ConstUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * ssh工具,执行ssh命令
 * @author: pizhihui
 * @datae: 2017-07-12
 * @version 0.1 简单版本
 */
public class SSHUtil {

    private static final Logger logger = LoggerFactory.getLogger(SSHUtil.class);
    private static final int port = 22;

    private static Connection getConnection(String ip, int port, String user, String password) {
        Connection con = null;
        try {
            con = new Connection(ip, port);
            con.connect(null, 12000, 12000);
            con.authenticateWithPassword(user, password);
        } catch (IOException e) {
            logger.error("ssh connect error : ", e.getMessage());
        }
        return con;
    }

    /**
     * 执行命令
     * @param cmd
     * @param ip 执行kvm install的ip,应为物理机的ip
     */
    public static void execute(String cmd, String ip) {
        execute(cmd, ip, ConstUtils.SSH_PORT_DEFAULT, ConstUtils.DEFAULT_USERNAME, ConstUtils.DEFAULT_PASSWORD, new DefaultSSHCallBack());
    }

    /**
     * 执行命令
     * @param cmd
     * @param ip
     * @param port
     * @param user
     * @param password
     */
    public static void execute(String cmd, String ip, int port, String user, String password, SSHCallBack callBack) {
        Connection con = null;
        Session session = null;
        try {
            con = getConnection(ip, port, user, password);
            session = con.openSession();
            callBack.call(session, cmd);
//            session.requestPTY("vt100", 80, 24, 640, 480, null);
//            //session.startShell();
//            session.execCommand(cmd); // 执行命令
//            InputStream stdout = new StreamGobbler(session.getStdout());
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdout));
//            String line = null;
//            int lineNbr = 1;
//            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(lineNbr + " : " + line); // 打印返回的内容
//                if (line.contains("Started Login Service")) { //重启完毕后,已经完成
//                    break;
//                }
//                lineNbr++;
//            }

        } catch (IOException e) {
            logger.error("execute cmd with {} error {}", cmd, e.getMessage(), e);
        } finally {
            if (null != con) {
                con.close();
            }
            if (null != session) {
                session.close();
            }
        }

    }

    public static void scpLocalToRemote(String ip, String locaFile, String remoteDir) {
        scpLocalToRemote(ip, ConstUtils.SSH_PORT_DEFAULT,
                ConstUtils.DEFAULT_USERNAME,
                ConstUtils.DEFAULT_PASSWORD,
                locaFile, remoteDir, "0644");
    }
    public static void scpLocalToRemote(String ip, int port, String user, String password,
                                        String locaFile,
                                        String remoteDir,
                                        String mode) {
        Connection con = null;
        Session session = null;
        try {
            con = getConnection(ip, port, user, password);
            SCPClient client = con.createSCPClient();
            client.put(locaFile, remoteDir, mode);
        } catch (IOException e) {
            logger.error("scp local {} to remote {} err", locaFile, remoteDir, e);
        } finally {
            if (null != con) {
                con.close();
            }

        }
    }

    /**
     * 回调函数,具体处理
     */
    public interface SSHCallBack {
        void call(Session session, String cmd);
    }

    public static class DefaultSSHCallBack implements SSHCallBack {

        @Override
        public void call(Session session, String cmd) {
            try {
                session.requestPTY("vt100", 80, 24, 640, 480, null);
                //session.startShell();
                session.execCommand(cmd); // 执行命令
                InputStream stdout = new StreamGobbler(session.getStdout());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdout));
                String line = null;
                int lineNbr = 1;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(lineNbr + " : " + line); // 打印返回的内容
                    if (line.contains("Started Login Service")) { //重启完毕后,已经完成
                        break;
                    }
                    lineNbr++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
