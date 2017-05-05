package com.jkb.commonlib.base.ui.action.frame;


import com.jkb.commonlib.base.frame.BasePresenter;

/**
 * 支持MVP框架的Action
 * Created by JustKiddingBaby on 2017/4/16.
 */

public interface IMVPAction<P extends BasePresenter> {

    /**
     * 初始化Presenter层
     */
    void initPresenter();

    /**
     * 返回Presenter对象
     */
    P getPresenter();

    /**
     * 是否支持MVP框架，默认false
     */
    boolean isSupportMVP();
}
