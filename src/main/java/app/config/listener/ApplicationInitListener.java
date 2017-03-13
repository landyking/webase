package app.config.listener;

import app.util.AppPropertiesUtils;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Description：<br/>
 *
 * @author: landy
 * @date: 2015/09/28 10:48
 * note:
 */
public class ApplicationInitListener implements ServletContextListener {
    static {
        AppPropertiesUtils.init();
    }

    public static final String TOMCAT_BASE = "catalina.base";
    public static final String INIT_ERROR = "InitError";

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            String appHome = null;
            //尝试获取csHome系统变量,若存在则直接使用该值作为csHome
            if (StringUtils.hasText(System.getProperty(AppPropertiesUtils.APP_HOME))) {
                appHome = System.getProperty(AppPropertiesUtils.APP_HOME).trim();
            } else {
                //不存在,则尝试获取catalina.base,若不成功则报错,将错误信息设置到application中.key为InitError
                String catalinaBase = System.getProperty(TOMCAT_BASE);
                Assert.hasText(catalinaBase, "获取属性" + TOMCAT_BASE + "失败");
                //若获取成功,则使用该值+csagent_work作为csHome
                appHome = new File(catalinaBase, AppPropertiesUtils.APP_HOME).getAbsolutePath();
            }
            //检查csHome是否存在,不存在则创建,创建失败则设置InitError
            File appHomeDir = new File(appHome);
            if (!appHomeDir.exists()) {
                Assert.isTrue(appHomeDir.mkdirs(), "创建目录" + appHome + "失败");
            }
            chekPermission(appHomeDir);
            //检查csHome以及csHome下面的所有文件,查看是否有权操作.任一无权则报错,设置InitError
            Iterable<File> subFilesTraverser = Files.fileTreeTraverser().children(appHomeDir);
            for (File one : subFilesTraverser) {
                chekPermission(one);
            }
            //创建必须的目录conf,data,logs,创建失败则报错,设置InitError
            File confDir = new File(appHomeDir, "conf");
            if (!confDir.exists()) {
                confDir.mkdir();
            }
            File dataDir = new File(appHomeDir, "data");
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            File logsDir = new File(appHomeDir, "logs");
            if (!logsDir.exists()) {
                logsDir.mkdir();
            }
            //初始化完毕,重新设置csHome系统变量
            System.setProperty(AppPropertiesUtils.APP_HOME, appHomeDir.getAbsolutePath());
            //将日志配置文件copy到csHome一份
            File logbackFile = new File(confDir, "logback.xml");
            if (!logbackFile.exists()) {
                InputStream resourceAsStream = getClass().getResourceAsStream("/logback-test.xml");
                if (resourceAsStream == null) {
                    resourceAsStream = getClass().getResourceAsStream("/logback.xml");
                }
                if (resourceAsStream != null) {
                    FileOutputStream out = new FileOutputStream(logbackFile);
                    ByteStreams.copy(resourceAsStream, out);
                    out.close();
                }
            }
            System.setProperty("logback.configurationFile", logbackFile.getAbsolutePath());
        } catch (Exception e) {
            ServletContext application = servletContextEvent.getServletContext();
            application.setAttribute(INIT_ERROR, "无法确定" + AppPropertiesUtils.APP_HOME + ":" + e.getMessage());
            System.setProperty(AppPropertiesUtils.APP_HOME, Files.createTempDir().getAbsolutePath());
            e.printStackTrace();
        }
    }

    private void chekPermission(File one) {
        Assert.isTrue(one.canRead(), one.getAbsolutePath() + "没有读权限");
        Assert.isTrue(one.canWrite(), one.getAbsolutePath() + "没有写权限");
        if (one.isDirectory()) {
            Assert.isTrue(one.canExecute(), one.getAbsolutePath() + "没有执行权限");
        }
    }

    public static String getAppHome() {
        return System.getProperty(AppPropertiesUtils.APP_HOME);
    }

    public static String getTomcatBase() {
        return System.getProperty(TOMCAT_BASE);
    }
}
