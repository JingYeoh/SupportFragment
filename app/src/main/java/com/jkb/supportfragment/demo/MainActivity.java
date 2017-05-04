package com.jkb.supportfragment.demo;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jkb.commonlib.base.BaseActivity;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.helper.AppLauncher;
import com.jkb.support.ui.SupportFragment;
import com.jkb.support.utils.LogUtils;
import com.jkb.supportfragment.demo.business.onboarding.OnBoardingFragment;

/**
 * 单Activity+多Fragment架构的主Activity
 */
public class MainActivity extends BaseActivity {

    @Override
    public int getRootViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            startFragment(AppLauncher.launchOnBoardingMain());
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public int getFragmentContentId() {
        return R.id.mainFrameContent;
    }
}
