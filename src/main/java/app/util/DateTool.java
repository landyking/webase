package app.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  <br/>
 *
 * @author: landy
 * @date: 2017/2/13 13:53
 * note:
 */
public class DateTool {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String nowDateTime() {
        return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(new Date());
    }

    public static String toDateTime(Date datetime) {
        return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(datetime);
    }

    public static String toHumanAgo(long ago) {
        int minUnit = 60 * 1000;
        int hourUnit = 60 * minUnit;
        int dayUnit = hourUnit * 24;
        StringBuilder sb = new StringBuilder();
        long day = ago / dayUnit;
        if (day > 0) {
            sb.append(day + "天");
        }
        long tmp = ago - day * dayUnit;
        long hour = tmp / hourUnit;
        if (hour > 0) {
            sb.append(hour + "时");
        }
        tmp = tmp - hour * hourUnit;
        long min = tmp / minUnit;
        if (min > 0) {
            sb.append(min + "分");
        }
        tmp = tmp - min * minUnit;
        long second = tmp / 1000;
        if (second > 0) {
            sb.append(second + "秒");
        }
        return sb.toString();
    }

    public static String toDateTime(long publishTime) {
        return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(new Date(publishTime));
    }
}
