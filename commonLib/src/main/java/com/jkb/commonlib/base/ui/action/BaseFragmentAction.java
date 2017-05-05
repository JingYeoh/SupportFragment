package com.jkb.commonlib.base.ui.action;

/**
 * 只有Fragment用到的Action
 * Created by JustKiddingBaby on 2017/4/16.
 */

public interface BaseFragmentAction {

    /**
     * 设置是否是全屏属性，true为全屏
     */
    boolean requestFullScreenStyle();

    /**
     * 设置是否允许沉浸式状态栏
     */
    boolean requestImmersiveStatusStyle();

    /**
     * 页面是否被销毁
     */
    boolean isActive();

}
