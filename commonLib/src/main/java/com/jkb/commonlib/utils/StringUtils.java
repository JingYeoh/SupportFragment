package com.jkb.commonlib.utils;

import android.text.TextUtils;

import com.jkb.support.utils.LogUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * String的工具类
 * Created by yj on 2017/5/5.
 */

public class StringUtils {

    /**
     * 字符串是否有空
     */
    public static boolean hasEmpty(String... values) {
        if (values == null || values.length == 0) return true;
        for (String item : values) {
            if (TextUtils.isEmpty(item)) return true;
        }
        return false;
    }

    /**
     * InputString转换String
     */
    public static String InputStreamToString(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
