package app.util;

import java.util.ArrayList;

/**
 *
 * @author: landy
 * @date: 2015/09/15 10:36
 * note:
 */
public class SpringSqlBuilder {
    private StringBuffer sql = new StringBuffer();
    private ArrayList<Object> arguments = new ArrayList<Object>();

    private SpringSqlBuilder() {
    }

    public static SpringSqlBuilder create() {
        return new SpringSqlBuilder();
    }

    public static SpringSqlBuilder create(String segment, Object... params) {
        return create().append(segment, params);
    }

    public SpringSqlBuilder append(String segment, Object... params) {
        sql.append(segment);
        if (params != null) {
            for (Object one : params) {
                arguments.add(one);
            }
        }
        return this;
    }

    public String toSql() {
        return sql.toString();
    }

    public Object[] toArguments() {
        return arguments.toArray();
    }

}
