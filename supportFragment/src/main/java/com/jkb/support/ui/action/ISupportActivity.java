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

    /**
     * 在finish之前是否隐藏最后一个Fragment.
     *
     * @return true表示Activity会一直会退到Activity本身的View然后进行finish操作
     * false表示Activity会回退到栈内只剩一个Fragment,然后再按onBackPressed然后直接finish.
     * 默认为false.
     */
    boolean isHideLashFragmentBeforeFinish();
}
