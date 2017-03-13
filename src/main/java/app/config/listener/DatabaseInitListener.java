package app.config.listener;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Description：<br/>
 *
 * @author: landy
 * @date: 2015/09/28 10:48
 * note:
 */
public class DatabaseInitListener implements ServletContextListener {
    private static String jdbcUrl;

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public static String getJdbcUrl() {
        return jdbcUrl;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Logger logger = LoggerFactory.getLogger(DatabaseInitListener.class);
        try {
            // Create the Flyway instance
            Flyway flyway = new Flyway();

            // Point it to the database
            jdbcUrl = "jdbc:h2:file:" + ApplicationInitListener.getAppHome() + "/data/db";
            logger.info("use jdbc url [{}]", jdbcUrl);
            String user = "sa";
            String password = "sa";
            flyway.setDataSource(jdbcUrl, user, password);
            // Start the migration
            flyway.migrate();
            logger.info("App database migrate over");
            System.setProperty("activejdbc.url", jdbcUrl);
            logger.info("set property activejdbc.url={}",jdbcUrl);
            System.setProperty("activejdbc.user", user);
            logger.info("set property activejdbc.user={}",user);
            System.setProperty("activejdbc.password", password);
            logger.info("set property activejdbc.password={}",password);
            String driver = "org.h2.Driver";
            System.setProperty("activejdbc.driver", driver);
            logger.info("set property activejdbc.driver={}",driver);
        } catch (Exception e) {
            logger.error("Init app database failure : {}", e.getMessage());
            throw new RuntimeException("Init app database failure", e);
        }
    }
}
