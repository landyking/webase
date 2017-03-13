package app.util;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

/**
 * Description：<br/>
 *
 * @author: landy
 * @date: 2016/12/21 23:58
 * note:
 */
public class ConfigBulk {
    private final ConfigBulk parent;


    public ConfigBulk(ConfigBulk parent) {
        this.parent = parent;
    }

    protected String searchLocalValue(BulkItem key) {
        return null;
    }

    private String searchValue(BulkItem key) {
        if (key != null) {
            String selfVal = searchLocalValue(key);
            if (Texts.hasText(selfVal)) {
                return selfVal;
            } else {
                if (parent != null) {
                    return parent.searchValue(key);
                }
            }
        }
        return null;
    }

    public int getInt(BulkItem key, int def) {
        String val = searchValue(key);
        if (Texts.hasText(val)) {
            Integer rst = Ints.tryParse(val);
            if (rst != null) {
                return rst.intValue();
            }
        }
        return def;
    }

    public int requireInt(BulkItem key) {
        return requireInt(key, "require param(int) : " + key);
    }

    public int requireInt(BulkItem key, String msg) {
        String val = searchValue(key);
        if (Texts.hasText(val)) {
            Integer rst = Ints.tryParse(val);
            if (rst != null) {
                return rst.intValue();
            }
        }
        throw new IllegalArgumentException(msg);
    }

    public boolean getBool(BulkItem key, boolean def) {
        String val = searchValue(key);
        if (Texts.hasText(val)) {
            Boolean rst = Boolean.valueOf(val);
            if (rst != null) {
                return rst.booleanValue();
            }
        }
        return def;
    }

    public boolean requireBool(BulkItem key) {
        return requireBool(key, "require param(boolean) : " + key);
    }

    public boolean requireBool(BulkItem key, String msg) {
        String val = searchValue(key);
        if (Texts.hasText(val)) {
            Boolean rst = Boolean.valueOf(val);
            if (rst != null) {
                return rst.booleanValue();
            }
        }
        throw new IllegalArgumentException(msg);
    }

    public String getText(BulkItem key, String def) {
        String val = searchValue(key);
        if (Texts.hasText(val)) {
            return val;
        }
        return def;
    }

    public String requireText(BulkItem key) {
        return requireText(key, "require param(text) : " + key);
    }

    public String requireText(BulkItem key, String msg) {
        String val = searchValue(key);
        if (Texts.hasText(val)) {
            return val;
        }
        throw new IllegalArgumentException(msg);
    }

    public long getLong(BulkItem key, long def) {
        String val = searchValue(key);
        if (Texts.hasText(val)) {
            Long rst = Longs.tryParse(val);
            if (rst != null) {
                return rst.longValue();
            }
        }
        return def;
    }

    public long requireLong(BulkItem key) {
        return requireLong(key, "require param(long) : " + key);
    }

    public long requireLong(BulkItem key, String msg) {
        String val = searchValue(key);
        if (Texts.hasText(val)) {
            Long rst = Longs.tryParse(val);
            if (rst != null) {
                return rst.longValue();
            }
        }
        throw new IllegalArgumentException(msg);
    }
}
