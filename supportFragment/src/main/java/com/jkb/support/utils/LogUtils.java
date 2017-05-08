package com.jkb.support.utils;

import android.util.Log;

/**
 * Log的工具类
 * Created by JustKiddingBaby on 2016/10/18.
 */

public class LogUtils {

    public static boolean isAllowToPrint = true;

    public static void i(String TAG, String value) {
        if (isAllowToPrint) {
            Log.i(TAG, value);
        }
    }

    public static void v(String TAG, String value) {
        if (isAllowToPrint) {
            Log.v(TAG, value);
        }
    }

    public static void w(String TAG, String value) {
        if (isAllowToPrint) {
            Log.w(TAG, value);
        }
    }

    public static void e(String TAG, String value) {
        if (isAllowToPrint) {
            Log.e(TAG, value);
        }
    }

    public static void d(String TAG, String value) {
        if (isAllowToPrint) {
            Log.d(TAG, value);
        }
    }

    public static void wtf(String TAG, String value) {
        if (isAllowToPrint) {
            Log.wtf(TAG, value);
        }
    }

    public static void i(Class<?> clz, String value) {
        i(clz.getSimpleName(), value);
    }

    public static void d(Class<?> clz, String value) {
        d(clz.getSimpleName(), value);
    }

    public static void v(Class<?> clz, String value) {
        v(clz.getSimpleName(), value);
    }

    public static void w(Class<?> clz, String value) {
        w(clz.getSimpleName(), value);
    }

    public static void e(Class<?> clz, String value) {
        e(clz.getSimpleName(), value);
    }

    public static void wtf(Class<?> clz, String value) {
        wtf(clz.getSimpleName(), value);
    }

    public static void d(Object object, String value) {
        d(object.getClass(), value);
    }

    public static void i(Object object, String value) {
        i(object.getClass(), value);
    }

    public static void v(Object object, String value) {
        v(object.getClass(), value);
    }

    public static void w(Object object, String value) {
        w(object.getClass(), value);
    }

    public static void e(Object object, String value) {
        e(object.getClass(), value);
    }

    public static void wtf(Object object, String value) {
        wtf(object.getClass(), value);
    }
}
