package app.model;

import app.util.ActiveJdbcMojo;
import app.util.DateTool;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

/**
 * <br/>
 *
 * @author: landy
 * @date: 2017/2/9 15:53
 * note:
 */
@Table("t_runtime_log")
@IdName("log_id")
public class RuntimeLog extends Model {
    public void setStartTime(String time) {
        this.set("start_time", time);
    }

    public void setEndTime(String time) {
        this.set("end_time", time);
    }

    public void setLogDesc(String desc) {
        this.set("log_desc", desc);
    }

    public static void addNewLog(String desc) {
        try (ActiveJdbcMojo mojo = new ActiveJdbcMojo()) {
            RuntimeLog runtimeLog = new RuntimeLog();
            runtimeLog.setStartTime(DateTool.nowDateTime());
            runtimeLog.setEndTime(DateTool.nowDateTime());
            runtimeLog.setLogDesc(desc);
            runtimeLog.insert();
        }

    }
}
