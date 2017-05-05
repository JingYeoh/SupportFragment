package com.jkb.commonlib.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间的工具类
 * Created by yj on 2017/5/5.
 */

public class TimeUtils {

    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 返回当前时间
     */
    public static String getCurrentTimeStr() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat(TIME_FORMAT, Locale.CHINA);
        return format.format(date);
    }

    /**
     * 得到系统当前时间
     */
    public static Date getCurrentTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(TIME_FORMAT, Locale.CHINA);
        String date = sDateFormat.format(new Date());
        return getDateFromStr(date);
    }

    /**
     * 字符串转换为时间
     */
    public static Date getDateFromStr(String dateStr) {
        SimpleDateFormat f = new SimpleDateFormat(TIME_FORMAT, Locale.CHINA);
        Date date = null;
        try {
            date = f.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
