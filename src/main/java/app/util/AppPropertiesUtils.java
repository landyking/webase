package app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <br/>
 *
 * @author: landy
 * @date: 2017/2/8 10:20
 * note:
 */
public class AppPropertiesUtils {
    public static final String APP_VERSION;
    public static final String APP_HOME;
    public static final String APP_NAME;
    public static final String APP_LOG;

    private static final String VERSION_PROPERTIES_FILE = "/app.properties";

    static {
        Properties prop = new Properties();
        InputStream stream = AppPropertiesUtils.class.getResourceAsStream(VERSION_PROPERTIES_FILE);
        if (stream != null) {
            try {
                prop.load(stream);
            } catch (IOException e) {
                System.out.println("properties load failure : classpath:" + VERSION_PROPERTIES_FILE);
            }
        } else {
            System.out.println("properties file not found : classpath:" + VERSION_PROPERTIES_FILE);
        }
        APP_VERSION = prop.getProperty("app.version", "unknown");
        APP_HOME = prop.getProperty("app.home", "appHome");
        APP_NAME = prop.getProperty("app.name", "unknown");
        System.setProperty("app.home", APP_HOME);
        APP_LOG = prop.getProperty("app.log", "app");
        System.setProperty("app.log", APP_LOG);
        System.out.println("properties load [app.version=" + APP_VERSION + "]");
    }

    public static void init() {

    }
}
