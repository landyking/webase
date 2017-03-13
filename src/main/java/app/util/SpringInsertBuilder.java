package app.util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: landy
 * @date: 2015/09/15 11:11
 * note:
 */
public class SpringInsertBuilder {
    private final String sqlInsert;
    private List<String> fields = new ArrayList<String>();
    private List<Map.Entry<Boolean, Object>> arguments = new ArrayList<Map.Entry<Boolean, Object>>();

    private SpringInsertBuilder(String insertSql) {
        this.sqlInsert = insertSql;
    }

    public static SpringInsertBuilder create(String insertSql) {
        return new SpringInsertBuilder(insertSql);
    }

    public SpringInsertBuilder hold(String field, Object param) {
        fields.add(field);
        arguments.add(new AbstractMap.SimpleEntry<Boolean, Object>(false, param));
        return this;
    }

    public SpringInsertBuilder direct(String field, String param) {
        fields.add(field);
        arguments.add(new AbstractMap.SimpleEntry<Boolean, Object>(true, param));
        return this;
    }

    public String toSql() {
        StringBuilder sb = new StringBuilder(sqlInsert);
        sb.append(" (");
        for (String f : fields) {
            sb.append(f);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(") values (");
        for (Map.Entry<Boolean, Object> one : arguments) {
            if (one.getKey()) {
                sb.append(one.getValue() + ",");
            } else {
                sb.append("?,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(')');
        return sb.toString();
    }

    public Object[] toArguments() {
        List<Object> rst = new ArrayList<Object>();
        for (Map.Entry<Boolean, Object> one : arguments) {
            if (!one.getKey()) {
                rst.add(one.getValue());
            }
        }
        return rst.toArray();
    }
}
