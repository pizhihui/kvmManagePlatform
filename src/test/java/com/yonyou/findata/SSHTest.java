package com.yonyou.findata;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.KnownHosts;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.junit.Before;
import org.junit.Test;

import javax.sound.sampled.Port;
import java.io.*;

/**
 * @author: pizhihui
 * @datae: 2017-07-12
 */
public class SSHTest {

    private static final String host = "192.168.197.128";
    private static final int port = 22;
    private static final String user = "cachecloud";
    private static final String password = "yonyou#123";

    private static final Connection con = null;



    @Test
    public void simpleTest() {
        Connection con = null;
        Session session = null;

        try {
            con = new Connection(host, port);
            con.connect();
            con.authenticateWithPassword(user, password);
            session = con.openSession();
            session.execCommand("netstat -nltp|grep redis-server");
            InputStream stdout = session.getStdout();
            InputStream sstdout = new StreamGobbler(stdout);
            BufferedReader bufStdOut = new BufferedReader(new InputStreamReader(sstdout));
            while (true) {
                String line = bufStdOut.readLine();
                if (null == line) {
                    break;
                }
                System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println(String.format("ssh failure %s", e.getMessage()));
        } finally {
            if (null != con) {
                con.close();
            }
            if (null != session) {
                session.close();
            }
        }

    }

}
