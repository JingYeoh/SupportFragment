package com.jkb.commonlib.base.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.jkb.commonlib.base.ui.action.BaseAction;
import com.jkb.commonlib.base.ui.action.BaseFragmentAction;
import com.jkb.commonlib.base.ui.action.frame.IMVVMAction;
import com.jkb.support.ui.SupportFragment;
import com.jkb.support.utils.LogUtils;

/**
 * Fragment的基类
 */

public abstract class BaseFragment<VM extends ViewDataBinding> extends SupportFragment implements BaseAction,
        BaseFragmentAction, IMVVMAction<VM> {

    //view
    protected View rootView;
    //data
    private VM mBinding;

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
        if (isSupportMVVM()) {
            mBinding = DataBindingUtil.inflate(inflater, getRootViewId(), container, false);
            rootView = mBinding.getRoot();
        } else {
            rootView = inflater.inflate(getRootViewId(), container, false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setFullScreenStyle();
        setImmersiveStatus();
        init(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            setFullScreenStyle();
            setImmersiveStatus();
        }
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
    private void setFullScreenStyle() {
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

    /**
     * 沉浸式状态栏
     */
    private void setImmersiveStatus() {
        Window window = mActivity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int flag_translucent_status = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (requestImmersiveStatusStyle()) {
                window.setFlags(flag_translucent_status, flag_translucent_status);//透明状态栏
            } else {
                window.clearFlags(flag_translucent_status);
            }
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
    public boolean requestImmersiveStatusStyle() {
        return true;
    }

    @Override
    public void showShortToast(String value) {
        if (TextUtils.isEmpty(value)) return;
        Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isSupportMVVM() {
        return false;
    }

    @Override
    final public VM getBinding() {
        return mBinding;
    }
}
