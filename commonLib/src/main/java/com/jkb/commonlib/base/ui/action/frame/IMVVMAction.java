package com.jkb.commonlib.base.ui.action.frame;

import android.databinding.ViewDataBinding;

/**
 * 支持MVVM框架的Action
 * Created by yj on 2017/4/14.
 */

public interface IMVVMAction<VM extends ViewDataBinding> {

    /**
     * 是否支持MVVM框架，即该页面是否使用了DataBinding框架，默认false
     *
     * @return true表示使用，否则表示不使用
     */
    boolean isSupportMVVM();

    /**
     * 返回binding对象
     */
    VM getBinding();
}
