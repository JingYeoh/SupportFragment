package com.jkb.commonlib.base.ui.action;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * 基类的Action
 */

public interface BaseAction {
    /**
     * 通过id返回View
     */
    View findViewById(@IdRes int id);

    /**
     * 设置根视图的id
     */
    @LayoutRes
    int getRootViewId();

    /**
     * 初始化
     */
    void init(Bundle savedInstanceState);

    /**
     * 初始化视图
     */
    void initView();

    /**
     * 初始化數據
     */
    void initData(Bundle savedInstanceState);

    /**
     * 初始化监听器
     */
    void initListener();

    /**
     * 显示返回结果
     */
    void showShortToast(String value);
}
