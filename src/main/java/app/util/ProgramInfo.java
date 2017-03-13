package app.util;

import com.google.common.collect.Lists;

import java.lang.management.ManagementFactory;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author: landy
 * @date: 2015/12/02 11:23
 * note:
 */
public class ProgramInfo {
    public static String getOSnfo() {
        Properties props = System.getProperties();
        String rst = String.format("%s | %s | %s", props.getProperty("os.name"), props.getProperty("os.arch"), props.getProperty("os.version"));
        return rst;
    }

    public static String getJVMVendor() {
        Properties props = System.getProperties();
        String javaVendor = props.getProperty("java.vendor");
        return javaVendor;
    }

    public static String getJVMVersion() {
        Properties props = System.getProperties();
        String javaVersion = props.getProperty("java.version");
        return javaVersion;
    }

    public static String getJVMHome() {
        Properties props = System.getProperties();
        String javaHome = props.getProperty("java.home");
        return javaHome;
    }

    public static String getCPUInfo() {
        return Runtime.getRuntime().availableProcessors() + "";
    }

    public static String getMemoryInfo() {
        Runtime r = Runtime.getRuntime();
        int mUnit = 1024 * 1024;
        String rst = String.format("Free:%sM Total:%sM Max:%sM",r.freeMemory()/mUnit, r.totalMemory() / mUnit, r.maxMemory() / mUnit);
        return rst;
    }

    public static String getPID() {
        try {
            String name = ManagementFactory.getRuntimeMXBean().getName();
            return name.split("@")[0];
        } catch (Exception e) {
            return "";
        }
    }

    public static String getUpTime() {
        long diff = ManagementFactory.getRuntimeMXBean().getUptime();
        long diffSeconds = (diff / 1000) % 60;
        long diffMinutes = (diff / (60 * 1000)) % 60;
        long diffHours = (diff / (60 * 60 * 1000)) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        StringBuffer sb = new StringBuffer();
        if (diffDays > 0) {
            sb.append(diffDays);
            sb.append("天");
        }
        if (diffHours > 0) {
            sb.append(diffHours);
            sb.append("时");
        }
        if (diffMinutes > 0) {
            sb.append(diffMinutes);
            sb.append("分");
        }
        sb.append(diffSeconds);
        sb.append("秒");
        return sb.toString();
    }

    public static int getThreadCount() {
        return ManagementFactory.getThreadMXBean().getThreadCount();
    }

    public static String getIpInfo() {
        return getAllLocalHostIP().toString();
    }

    public static String getOSUser() {
        Properties props = System.getProperties();
        return props.getProperty("user.name");
    }

    public static List<String> getAllLocalHostIP() {
        List<String> ips = Lists.newArrayList();
        try {
            Enumeration<NetworkInterface> nets = NetworkInterface
                    .getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                if (null != netint.getHardwareAddress()) {
                    List<InterfaceAddress> list = netint.getInterfaceAddresses();
                    for (InterfaceAddress interfaceAddress : list) {
                        String localip = interfaceAddress.getAddress().toString();
                        ips.add(localip);
                    }
                }
            }
        } catch (SocketException e) {
        }
        return ips;
    }
}
