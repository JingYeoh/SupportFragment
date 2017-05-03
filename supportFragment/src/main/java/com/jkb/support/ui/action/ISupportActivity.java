package com.jkb.support.ui.action;

import android.support.annotation.IdRes;

/**
 * 支持Support架构中只有Activity需要的Action
 * Created by JustKiddingBaby on 2017/4/15.
 */

public interface ISupportActivity {
    /**
     * 得到Fragment的视图的载体id
     * 用于start方法的使用
     */
    @IdRes
    int getFragmentContentId();
}
