package app.util;


import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FreeworkDateEditor extends PropertyEditorSupport {
    public static final FreeworkDateEditor INSTANCE = new FreeworkDateEditor();
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private final long startTime;


    private FreeworkDateEditor() {
        try {
            startTime = new SimpleDateFormat(
                    YYYY_MM_DD_HH_MM_SS).parse("1970-01-01 00:00:00").getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Parse the Date from the given text, using the specified DateFormat.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!Texts.hasText(text)) {
            // Treat empty String as null value.
            setValue(null);
        } else if (text.trim().matches("\\d+")) {
            Long mis = Long.parseLong(text.trim());
            if (mis > startTime) {
                setValue(new Date(mis));
            } else {
                throw new IllegalArgumentException("Could not parse date: " + text);
            }
        } else {
            try {
                Date date = parseDate(text);
                setValue(date);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
            }
        }
    }

    public static Date parseDate(String text) {
        try {
            return parseDate(text, new String[]{YYYY_MM_DD_HH_MM_SS, "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd HH", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyyMM", "yyyyMMdd", "yyyyMMddHHmmss", "HH:mm"});
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static Date parseDate(String str, String[] parsePatterns) throws ParseException {
        if (str != null && parsePatterns != null) {
            SimpleDateFormat parser = new SimpleDateFormat();
            parser.setLenient(true);
            ParsePosition pos = new ParsePosition(0);

            for (int i = 0; i < parsePatterns.length; ++i) {
                String pattern = parsePatterns[i];
                if (parsePatterns[i].endsWith("ZZ")) {
                    pattern = pattern.substring(0, pattern.length() - 1);
                }

                parser.applyPattern(pattern);
                pos.setIndex(0);
                String str2 = str;
                if (parsePatterns[i].endsWith("ZZ")) {
                    for (int date = indexOfSignChars(str, 0); date >= 0; date = indexOfSignChars(str2, date)) {
                        str2 = reformatTimezone(str2, date);
                        ++date;
                    }
                }

                Date var9 = parser.parse(str2, pos);
                if (var9 != null && pos.getIndex() == str2.length()) {
                    return var9;
                }
            }

            throw new ParseException("Unable to parse the date: " + str, -1);
        } else {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
    }

    private static int indexOfSignChars(String str, int startPos) {
        int idx = org.apache.commons.lang.StringUtils.indexOf(str, '+', startPos);
        if (idx < 0) {
            idx = org.apache.commons.lang.StringUtils.indexOf(str, '-', startPos);
        }

        return idx;
    }

    private static String reformatTimezone(String str, int signIdx) {
        String str2 = str;
        if (signIdx >= 0 && signIdx + 5 < str.length() && Character.isDigit(str.charAt(signIdx + 1)) && Character.isDigit(str.charAt(signIdx + 2)) && str.charAt(signIdx + 3) == 58 && Character.isDigit(str.charAt(signIdx + 4)) && Character.isDigit(str.charAt(signIdx + 5))) {
            str2 = str.substring(0, signIdx + 3) + str.substring(signIdx + 4);
        }

        return str2;
    }

    public static String formatHHMM(Object date) {
        DateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static String formatYYYYMMDD(Object date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String formatYYYYMMDDHHMMSS(Object date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String formatLongStr(Date date) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }

    /**
     * Format the Date as String, using the specified DateFormat.
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null ? new SimpleDateFormat(
                YYYY_MM_DD_HH_MM_SS).format(value) : "");
    }

    public static void main(String[] args) {
        /*System.out.println("hello");
        String time="2014-9-11 01:10:00";
        Date date = null;
        try {
            date = DateUtils.parseDate(time, YYYY_MM_DD_HH_MM_SS, "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd HH");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date.toLocaleString());*/
    }
}