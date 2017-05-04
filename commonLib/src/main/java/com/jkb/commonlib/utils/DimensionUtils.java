package com.jkb.commonlib.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 尺寸转换工具类
 * Created by yj on 2017/5/4.
 */

public class DimensionUtils {
    /**
     * px转dp
     */
    public static int px2dp(Context context, float pxValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue,
                context.getResources().getDisplayMetrics());
    }

    /**
     * px转sp
     */
    public static int px2sp(Context context, float pxValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, pxValue,
                context.getResources().getDisplayMetrics());
    }

    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * sp转px
     */
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
