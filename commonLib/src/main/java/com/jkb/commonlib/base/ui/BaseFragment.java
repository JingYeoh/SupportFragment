package com.jkb.commonlib.base.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.jkb.commonlib.base.ui.action.BaseAction;
import com.jkb.commonlib.base.ui.action.BaseFragmentAction;
import com.jkb.support.ui.SupportFragment;
import com.jkb.support.utils.LogUtils;

/**
 * Fragment的基类
 */

public abstract class BaseFragment extends SupportFragment implements BaseAction,
        BaseFragmentAction {

    //view
    protected View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        LogUtils.d(TAG, "onCreateView");
        rootView = inflater.inflate(getRootViewId(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setWindowStyle();
        init(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) setWindowStyle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initView();
        initData(savedInstanceState);
        initListener();
    }

    /**
     * 设置窗口是否全屏
     */
    private void setWindowStyle() {
        Window window = mActivity.getWindow();
        if (requestFullScreenStyle()) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams
                    .FLAG_FORCE_NOT_FULLSCREEN);
        }
    }

    @Override
    public View findViewById(@IdRes int id) {
        return rootView.findViewById(id);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public boolean requestFullScreenStyle() {
        return false;
    }

    @Override
    public void showShortToast(String value) {
        if (TextUtils.isEmpty(value)) return;
        Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
    }
}
