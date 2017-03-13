package app.model;

import app.constant.AppParamEnum;
import app.util.ActiveJdbcMojo;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

/**
 * <br/>
 *
 * @author: landy
 * @date: 2017/2/9 15:54
 * note:
 */
@Table("t_app_param")
@IdName("param_key")
public class AppParam extends Model {

    public void setParamValue(String value) {
        this.set("param_value", value);
    }

    public static Integer getAppParamValueInt(AppParamEnum appParamEnum) {
        try (ActiveJdbcMojo mojo = new ActiveJdbcMojo()) {
            return Integer.parseInt(getAppParamValueStr(appParamEnum));
        }
    }

    public static String getAppParamValueStr(AppParamEnum appParamEnum) {
        try (ActiveJdbcMojo mojo = new ActiveJdbcMojo()) {
            AppParam param = AppParam.findById(appParamEnum);
            return param.getString("param_value");
        }
    }
}
