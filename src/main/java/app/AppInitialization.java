package app;

import app.model.RuntimeLog;
import app.service.AppDataInitService;
import app.service.TestDataService;
import app.util.Texts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <br/>
 *
 * @author: landy
 * @date: 2017/2/7 16:36
 * note:
 */
public class AppInitialization extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AppInitialization.class);

    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        AppDataInitService appDataInitService = wac.getBean(AppDataInitService.class);
        appDataInitService.init();
        if (Texts.hasText(System.getProperty("needTestData"))) {
            TestDataService bean = wac.getBean(TestDataService.class);
            bean.initTestData();
        }
        RuntimeLog.addNewLog("#############系统启动###########");
        LOG.info("##############################################");
        LOG.info("##########  AppInitialization Over  ##########");
        LOG.info("##############################################");
    }

    @Override
    public void destroy() {
        super.destroy();
        LOG.info("##############################################");
        LOG.info("##########  Stop Over  ##########");
        LOG.info("##############################################");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
