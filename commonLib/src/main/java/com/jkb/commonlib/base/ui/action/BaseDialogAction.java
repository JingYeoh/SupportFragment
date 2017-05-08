package com.jkb.commonlib.base.ui.action;

import android.support.annotation.StyleRes;

/**
 * DialogFragment的Action
 * Created by yj on 2017/5/8.
 */

public interface BaseDialogAction extends BaseAction {
    /**
     * 返回style的资源id
     */
    @StyleRes
    int getStyleResId();

    /**
     * 点击外部是否取消视图的显示
     * 默认：true
     */
    boolean isCanceledOnTouchOutside();

    /**
     * 返回Window视图的gravy属性
     * 默认：Gravity.CENTER
     */
    int getWindowGravy();
}
