package com.jkb.supportfragment.demo;

import android.os.Bundle;

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
            SupportFragment fragment = AppLauncher.launchOnBoardingPlaceHolder(AppConfig.OnBoardType.ONE);
            LogUtils.d(this, "fragment=" + fragment);
            startFragment(OnBoardingFragment.newInstance());
//            startFragment(OnBoardingPlaceHolderFragment.newInstance(AppConfig.OnBoardType.ONE));
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
