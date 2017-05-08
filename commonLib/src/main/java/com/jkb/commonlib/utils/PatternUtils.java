package com.jkb.commonlib.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * 正则表达式的工具类
 * Created by yj on 2017/4/28.
 */

public class PatternUtils {

    //手机号
    private static final String MOBILE = "^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$";
    //网址
    private static final String URL = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+\" +\n" + "   " +
            "\"([A-Za-z0-9-~\\\\/])\" + \"+$";
    //本地图片路径
    private static final String URI_PATH_IMG = "^([Ff][Ii][Ll][Ee]://).*.[^/:/?/\"/>/</*]*/*." +
            "([Gg][Ii][Ff]|[Jj][Pp][Gg]|[Bb][Mm][Pp]|[Pp][Nn][Gg])";

    /**
     * 是否匹配网址
     *
     * @param url 待检验字符串
     */
    public static boolean isMatchUrl(String url) {
        if (TextUtils.isEmpty(url)) return false;
        Pattern pattern = Pattern.compile(URL);
        return pattern.matcher(url).matches();
    }

    /**
     * 是否匹配本地图片路径
     *
     * @param path 待检验路径
     */
    public static boolean isMatchLocalPicture(String path) {
        if (TextUtils.isEmpty(path)) return false;
        Pattern pattern = Pattern.compile(URI_PATH_IMG);
        return pattern.matcher(path).matches();
    }

    /**
     * 是否匹配手机号码
     */
    public static boolean isMatchPhoneNumber(String phone) {
        if (TextUtils.isEmpty(phone)) return false;
        Pattern pattern = Pattern.compile(MOBILE);
        return pattern.matcher(phone).matches();
    }
}
