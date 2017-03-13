package app.service;

import app.constant.AppParamConstans;
import app.constant.AppParamEnum;
import app.model.AppParam;
import app.util.ActiveJdbcMojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <br/>
 *
 * @author: landy
 * @date: 2017/2/15 9:10
 * note:
 */
@Service
public class AppDataInitService {

    private static final Logger LOG = LoggerFactory.getLogger("AppDataInit");

    public void init() {
        try (ActiveJdbcMojo mojo = new ActiveJdbcMojo()) {
            LOG.info("app data init start");
            AppParam localDeliveryServiceListenPort = AppParam.findById(AppParamEnum.LOCAL_DELIVERY_SERVICE_LISTEN_PORT);
            if (localDeliveryServiceListenPort == null) {
                AppParam.create("param_key", AppParamEnum.LOCAL_DELIVERY_SERVICE_LISTEN_PORT, "param_desc", "本机投递服务监听端口", "param_value", AppParamConstans.DEFAULT_LOCAL_DELIVERY_SERVICE_LISTEN_PORT)
                        .insert();
                LOG.info("init param [{}:{}]", AppParamEnum.LOCAL_DELIVERY_SERVICE_LISTEN_PORT, AppParamConstans.DEFAULT_LOCAL_DELIVERY_SERVICE_LISTEN_PORT);
            }
            AppParam adminLoginUserName = AppParam.findById(AppParamEnum.ADMIN_LOGIN_USER_NAME);
            if (adminLoginUserName == null) {
                AppParam.create("param_key", AppParamEnum.ADMIN_LOGIN_USER_NAME, "param_desc", "后台登录默认账号", "param_value", AppParamConstans.DEFAULT_ADMIN_LOGIN_USER)
                        .insert();
                LOG.info("init param [{}:{}]", AppParamEnum.ADMIN_LOGIN_USER_NAME, AppParamConstans.DEFAULT_ADMIN_LOGIN_USER);
            }
            AppParam adminLoginUserPass = AppParam.findById(AppParamEnum.ADMIN_LOGIN_USER_PASS);
            if (adminLoginUserPass == null) {
                AppParam.create("param_key", AppParamEnum.ADMIN_LOGIN_USER_PASS, "param_desc", "后台登录默认密码", "param_value", AppParamConstans.DEFAULT_ADMIN_LOGIN_PASS)
                        .insert();
                LOG.info("init param [{}:{}]", AppParamEnum.ADMIN_LOGIN_USER_PASS, AppParamConstans.DEFAULT_ADMIN_LOGIN_PASS);
            }
            LOG.info("app data init over");
        }
    }
}
