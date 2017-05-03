package com.jkb.support.utils;

import android.util.Log;

/**
 * Log的工具类
 * Created by JustKiddingBaby on 2016/10/18.
 */

public class LogUtils {

    private static boolean isAllowToPrint = true;

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
        String TAG = clz.getSimpleName();
        i(TAG, value);
    }

    public static void d(Class<?> clz, String value) {
        String TAG = clz.getSimpleName();
        d(TAG, value);
    }

    public static void v(Class<?> clz, String value) {
        String TAG = clz.getSimpleName();
        v(TAG, value);
    }

    public static void w(Class<?> clz, String value) {
        String TAG = clz.getSimpleName();
        w(TAG, value);
    }

    public static void e(Class<?> clz, String value) {
        String TAG = clz.getSimpleName();
        e(TAG, value);
    }

    public static void wtf(Class<?> clz, String value) {
        String TAG = clz.getSimpleName();
        wtf(TAG, value);
    }

    public static void d(Object object, String value) {
        String TAG = object.getClass().getSimpleName();
        d(TAG, value);
    }

    public static void i(Object object, String value) {
        String TAG = object.getClass().getSimpleName();
        i(TAG, value);
    }
}
