package com.jkb.support.ui.action;

import android.view.KeyEvent;

/**
 * 该Support架构中只有Fragment需要的Action
 * Created by JustKiddingBaby on 2017/4/15.
 */

public interface ISupportFragment {
    /**
     * 监听返回按键，可在任意地方拦截
     */
    void onBackPressed();

    /**
     * 监听按键点击事件的回调.
     */
    boolean onKeyDown(int keyCode, KeyEvent event);

    /**
     * 返回Fragment的TAG
     */
    String getFragmentTAG();

    /**
     * 返回请求码
     */
    int getRequestCode();
}
