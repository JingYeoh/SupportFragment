package com.jkb.commonlib.base.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.jkb.commonlib.R;
import com.jkb.commonlib.base.ui.action.BaseDialogAction;
import com.jkb.commonlib.base.ui.action.frame.IMVVMAction;

/**
 * DialogFragment的基类
 * Created by yj on 2017/5/8.
 */

public abstract class BaseDialogFragment<VM extends ViewDataBinding> extends DialogFragment implements
        BaseDialogAction, IMVVMAction<VM> {

    protected String TAG = this.getClass().getSimpleName();

    private VM mBinding;
    protected Activity mActivity;
    protected Dialog mDialog;
    private View mRootView;
    protected Context mContext;
    protected Context mApplicationContext;
    protected FragmentManager mParentFm;
    protected FragmentManager mChildFm;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        mDialog = new Dialog(mActivity, getStyleResId());
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if (isSupportMVVM()) {
            mBinding = DataBindingUtil.inflate(inflater, getRootViewId(), null, false);
            mRootView = mBinding.getRoot();
        } else {
            mRootView = inflater.inflate(getRootViewId(), null, false);
        }
        mDialog.setContentView(mRootView);
        initWindowConfig();//初始化window配置
        init(savedInstanceState);
        return mDialog;
    }

    /**
     * 初始化window配置
     */
    private void initWindowConfig() {
        Window window = mDialog.getWindow();
        if (window == null) return;
        window.setBackgroundDrawableResource(android.R.color.transparent);//防止宽度无法全屏
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        window.setGravity(getWindowGravy());
        mDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParentFm = getFragmentManager();
        mChildFm = getChildFragmentManager();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        mApplicationContext = context.getApplicationContext();
        mActivity = (Activity) context;
    }

    @Override
    public int getToolbarViewId() {
        return 0;
    }

    @Override
    public int getStyleResId() {
        return R.style.dialog_style;
    }

    @Override
    public boolean isCanceledOnTouchOutside() {
        return true;
    }

    @Override
    public int getWindowGravy() {
        return Gravity.CENTER;
    }

    @Override
    public View findViewById(@IdRes int id) {
        return mRootView.findViewById(id);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initView();
        initData(savedInstanceState);
        initListener();
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
