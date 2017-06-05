package com.jkb.commonlib.base.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.Toast;

import com.jkb.commonlib.base.ui.action.BaseAction;
import com.jkb.support.ui.SupportActivity;
import com.jkb.support.utils.LogUtils;

/**
 * 基类的Activity
 */

public abstract class BaseActivity extends SupportActivity implements BaseAction {

    //system
    protected Context context;
    protected Context applicationContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(TAG, "onCreate");
        context = this;
        applicationContext = getApplicationContext();
        setContentView(getRootViewId());
        init(savedInstanceState);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initView();
        initData(savedInstanceState);
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showShortToast(String value) {
        if (value != null && !value.trim().isEmpty()) {
            Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getToolbarViewId() {
        return 0;
    }
}
