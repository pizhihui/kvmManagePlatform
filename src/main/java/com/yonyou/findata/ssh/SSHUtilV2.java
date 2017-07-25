package com.yonyou.findata.ssh;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.yonyou.findata.exception.SSHException;
import com.yonyou.findata.util.ConstUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * ssh工具,执行ssh命令,升级版
 * @author: pizhihui
 * @datae: 2017-07-22
 * @version 0.2 升级版本
 */
public class SSHUtilV2 {

    private static final Logger logger = LoggerFactory.getLogger(SSHUtilV2.class);
    private static final int port = 22;
    private static final int connectTimeout = 12000;
    private static final int kexTimeout = 120000;

    // @v0.2 ssh命令提交到该线程池执行,执行多条命令
    private static ThreadPoolExecutor taskPool = new ThreadPoolExecutor(200, 200,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1000),
            new ThreadFactoryBuilder().setDaemon(false).setNameFormat("SSH-%d").build());

    /**
     * 获取ssh连接
     */
    private static Connection getConnection(String ip, int port, String user, String password) {
        Connection con = null;
        try {
            con = new Connection(ip, port);
            con.connect(null, connectTimeout, kexTimeout);
            con.authenticateWithPassword(user, password);
        } catch (IOException e) {
            logger.error("ssh connect error : ", e.getMessage());
        }
        return con;
    }

    public static Result execute(String cmd, String ip) {
        return execute(ip, ConstUtils.DEFAULT_SSH_PORT, ConstUtils.DEFAULT_USERNAME, ConstUtils.DEFAULT_PASSWORD, (session) -> session.executeCmd(cmd, null));
    }
    public static Result execute(String cmd, String ip, int port) {
        return execute(ip, port, ConstUtils.DEFAULT_USERNAME, ConstUtils.DEFAULT_PASSWORD, (session) -> session.executeCmd(cmd, null));
    }
    public static Result execute(String cmd, String ip, int port, String user, String password) {
       return execute(ip, port, user, password, (session) -> session.executeCmd(cmd, null));
    }

//    public static Result[] execute(String[] cmds, String ip, int port, String user, String password) {
//        Result[] result = new Result[cmds.length];
//        for (final int i = 0; i < cmds.length; i++) {
//            result[i] = execute(ip, port, user, password, (session) -> session.executeCmd(cmds[i], null));
//        }
//        return result;
//    }

    /**
     * 执行命令
     */
    public static Result execute(String ip, int port, String user, String password, SSHCallBack callBack) {
        Connection con = null;
        try {
            con = getConnection(ip, port, user, password);
            Result res = callBack.call(new SSHSession(con));
            return res;
        } catch (Exception e) {

        } finally {
            if (null != con) {
                con.close();
            }
        }
        return null;

    }

    public static void scpLocalToRemote(String ip, String locaFile, String remoteDir) {
        scpLocalToRemote(ip, ConstUtils.DEFAULT_SSH_PORT,
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


    private static String defaultProcess(InputStream stdOut, InputStream stdErr) {
        StringBuilder sb = new StringBuilder();
        LineProcessor lineProcessor = (line, lineNbr) -> {
            if (lineNbr > 1) {
                sb.append(System.lineSeparator());
            }
            sb.append(line);
        };
        processStream(stdOut, lineProcessor);
        if (sb.length() > 0) {
            return sb.toString();
        }
        processStream(stdErr, lineProcessor);
        if (sb.length() > 0) {
            logger.error("execute cmd  error: ", sb);
            return sb.toString();
        }
        return null;
    }

    private static void processStream(InputStream in, LineProcessor processor) {
        try (InputStream stdout = new StreamGobbler(in);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdout));){
            String line ;
            int lineNbr = 1;
            while ((line = bufferedReader.readLine()) != null) {
                processor.process(line, lineNbr);
                lineNbr++;
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 回调函数,处理具体任务
     */
    public interface SSHCallBack {
        Result call(SSHSession session) throws Exception;
    }

    /**
     * 处理具体得到的内容,可以执行多条命令
     */
    public static class SSHSession {
        private Connection conn;

        public SSHSession(Connection conn) {
            this.conn = conn;
        }

        // 单线程的,执行多个命令的时候会有问题
       /* public void executeCmd( String cmd, LineProcessor processor) {
            Session session = null;
            try {
                session = conn.openSession();
                session.execCommand(cmd);
                InputStream stdout = new StreamGobbler(session.getStdout());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdout));
                String line = null;
                int lineNbr = 1;
                while ((line = bufferedReader.readLine()) != null) {
                    processor.process(line, lineNbr);
                    lineNbr++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }*/

        /**
         * 执行命令,可以执行多条命令
         * @param cmd
         * @param processor
         * @return
         */
        public Result executeCmd(final String cmd, final LineProcessor processor) {
            final Session session ;
            Result res = null;
            try {
                session = conn.openSession();
                // 提交执行ssh命令任务
                Future<Result> future = taskPool.submit(() -> {
                    session.execCommand(cmd);
                    logger.info("start execute cmd: {} ", cmd);
                    // 自己处理获得内容
                    if (processor != null) {
                        processStream(session.getStdout(), processor);
                    } else { // 默认处理内容, 即结果返回
                        String s = defaultProcess(session.getStdout(), session.getStderr());
                        return new Result(true, s);
                    }
                    //failure
                    return new Result(true, null);

                });

                res = future.get(1000, TimeUnit.MILLISECONDS);
                future.cancel(true);
            } catch (Exception e) {
                logger.error("get ssh cmd error: ", e);
                //throw new SSHException(e);
            }
            // 返回最终的结果
            return res;
        }

    }


    public interface LineProcessor {
        void process(String line, int lineNbr);
    }


    /**
     * ssh执行结果封装
     */
    public static class Result {
        private boolean success;
        private String message;
        private Exception exception;

        public Result(boolean success) {
            this.success = false;
        }
        public Result(boolean success, String msg) {
            this.success = success;
            this.message = msg;
        }
        public Result(boolean success, String msg, Exception e) {
            this.success = success;
            this.message = msg;
            this.exception = e;
        }
        public Result(Exception e) {
            this.success = false;
            this.exception = e;
        }
        public boolean isSuccess() {
            return success;
        }
        public void setSuccess(boolean success) {
            this.success = success;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public Exception getException() {
            return exception;
        }
        public void setException(Exception e) {
            this.exception = e;
        }
    }



}
