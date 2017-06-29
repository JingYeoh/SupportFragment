package com.jkb.commonlib.base.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jkb.commonlib.base.ui.action.BaseAction;
import com.jkb.commonlib.base.ui.action.BaseFragmentAction;
import com.jkb.commonlib.base.ui.action.frame.IMVVMAction;
import com.jkb.commonlib.ui.annotation.SupportWindow;
import com.jkb.commonlib.ui.injection.SupportContentInjection;
import com.jkb.commonlib.ui.injection.SupportWindowInjection;
import com.jkb.support.ui.SupportFragment;
import com.jkb.support.utils.SLogUtils;

/**
 * Fragment的基类
 */
@SupportWindow()
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
        SLogUtils.d(TAG, "onCreateView");
        initContentView();
        return rootView;
    }

    /**
     * 初始化ContentView
     */
    private void initContentView() {
        rootView = SupportContentInjection.injectContentView(this, mActivity);
        if (rootView == null) {
            rootView = SupportContentInjection.injectContentView(mActivity, getRootViewId(),
                    getToolbarViewId());
        }
        //设置MVVM的支持
        if (isSupportMVVM() && rootView != null) {
            ViewGroup contentGroup = (ViewGroup) rootView;
            int contentPosition = contentGroup.getChildCount() > 1 ? 1 : 0;
            View child = ((ViewGroup) rootView).getChildAt(contentPosition);
            mBinding = DataBindingUtil.bind(child);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) SupportWindowInjection.injectWindow(this, mActivity);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void init(Bundle savedInstanceState) {
        SupportWindowInjection.injectWindow(this, mActivity);
//        AndroidBug5497Workaround.assistActivity(rootView);
        initView();
        initData(savedInstanceState);
        initListener();
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

    @Override
    public int getRootViewId() {
        return 0;
    }

    @Override
    public int getToolbarViewId() {
        return 0;
    }
}
