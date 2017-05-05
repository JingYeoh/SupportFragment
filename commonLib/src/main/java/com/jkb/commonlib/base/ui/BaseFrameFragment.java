package com.jkb.commonlib.base.ui;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jkb.commonlib.base.frame.BasePresenter;
import com.jkb.commonlib.base.ui.action.frame.IMVPAction;

/**
 * 支持MVP+MVVM的Fragment基类
 * Created by yj on 2017/5/5.
 */

public abstract class BaseFrameFragment<P extends BasePresenter, VM extends ViewDataBinding> extends BaseFragment<VM>
        implements IMVPAction<P> {

    protected BasePresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.destroy();
        mPresenter = null;
    }

    @Override
    final public boolean isSupportMVVM() {
        return true;
    }

    @Override
    final public boolean isSupportMVP() {
        return true;
    }

    /**
     * 返回Presenter
     */
    final public P getPresenter() {
        return (P) mPresenter;
    }
}
