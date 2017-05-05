package com.jkb.commonlib.base.frame;

/**
 * View基类
 * Created by yj on 2017/5/5.
 */

public interface BaseView<T extends BasePresenter> {
    /**
     * 是否被销毁
     */
    boolean isActive();

    /**
     * 显示Toast
     */
    void showShortToast(String value);

    /**
     * 注入P层
     */
    void setPresenter(T presenter);
}
