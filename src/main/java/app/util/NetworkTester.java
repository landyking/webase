package app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.net.*;
import java.sql.Driver;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkTester {

    private static Logger logger = LoggerFactory.getLogger(NetworkTester.class);

    public static boolean connectDirectTest(String host, int port, int timeoutSecond) {
        return connectTest(host, port, timeoutSecond, null);
    }

    public static boolean connectProxyTest(String host, int port, int timeoutSecond, String socksHost, int socksPort) {
        SocketAddress proxy = new InetSocketAddress(socksHost, socksPort);
        Proxy socksProxy = new Proxy(Proxy.Type.SOCKS, proxy);
        return connectTest(host, port, timeoutSecond, socksProxy);
    }

    public static boolean connectTest(String host, int port, int timeout, Proxy socksProxy) {
        boolean status = false;
        try {
            Socket socket = null;
            if (socksProxy != null) {
                socket = new Socket(socksProxy);
            } else {
                socket = new Socket();
            }
            socket.connect(new InetSocketAddress(host, port), timeout);

            logger.info("IP:{} Port:{}正常连接", host, port);
            status = socket.isConnected();
            socket.close();
        } catch (Exception e) {
            logger.error("IP:{} Port:{}连接失败", host, port);
        }
        return status;
    }


    public static boolean connectTest(String host) {
        boolean status = false;
        int timeout = 2000;// 设置验证IP连通延迟时间
        try {
            if (InetAddress.getByName(host).isReachable(timeout)) {
                logger.debug("IP:{}正常连接", host);
                status = true;
            } else {
                logger.debug("IP:{}无法连接", host);
                status = false;
            }
        } catch (Exception e) {
            logger.error("IP:{}连接失败", host);
        }
        return status;
    }

    public static boolean ipCheck(String ip) {
        Pattern pattern = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$");
        Matcher matcher = pattern.matcher(ip);
        boolean b = matcher.matches();
        return b;
    }

    public static boolean isLocalPortAvailable(int port) {
        ServerSocket serverSocket = null;
        boolean rst = false;
        try {
            serverSocket = new ServerSocket(port);
            rst = true;
        } catch (Exception e) {
            logger.info("端口{}测试失败:" + e.getMessage(), port);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (Exception e) {
                }
            }
        }

        return rst;
    }

    public static Tuple<Boolean, String> isOracleJdbcAvailable(String url, String driver, String username, String password) {
        try {
            SimpleDriverDataSource ds = new SimpleDriverDataSource();
            ds.setDriverClass((Class<? extends Driver>) Class.forName(driver));
            ds.setUrl(url);
            ds.setUsername(username);
            ds.setPassword(password);
            new JdbcTemplate(ds).execute("select 1 from dual");
            return Tuple.newOne(true, "success");
        } catch (Exception e) {
            return Tuple.newOne(false, e.getMessage());
        }
    }

    public static boolean connectFtpTest(String host, int port, String username, String password) {
        boolean succes = false;
        /*FtpTool ftpTool = new FtpTool(host, port, username, password);
        try {
            ftpTool.connect();
            succes = true;
        } catch (Exception e) {
            logger.info("使用{}/{}登录ftp服务器{}:{}失败! {}", username, password, host, port, e.getMessage());
        } finally {
            ftpTool.disconnect();
        }*/
        return succes;
    }
}
