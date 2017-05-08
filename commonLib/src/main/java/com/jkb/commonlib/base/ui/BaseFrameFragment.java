package com.jkb.commonlib.base.ui;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jkb.commonlib.base.frame.BasePresenter;
import com.jkb.commonlib.base.ui.action.frame.IMVPAction;

/**
 * 支持MVP+MVVM的Fragment基类
 * Created by yj on 2017/5/5.
 */

public abstract class BaseFrameFragment<P extends BasePresenter, VM extends ViewDataBinding> extends BaseFragment<VM>
        implements IMVPAction<P> {

    protected BasePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initPresenter();
        return view;
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
