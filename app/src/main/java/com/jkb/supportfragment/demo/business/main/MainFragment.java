package com.jkb.supportfragment.demo.business.main;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.app.AppManager;
import com.jkb.commonlib.base.ui.BaseFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.helper.AppLauncher;
import com.jkb.supportfragment.demo.R;
import com.jkb.supportfragment.demo.view.slidemenu.SlideMenuLayout;

/**
 * App主页
 * Created by yj on 2017/5/12.
 */
@Route(path = AppConfig.RouterPath.APP_MAIN)
public class MainFragment extends BaseFragment {

    private SlideMenuLayout slideMenuLayout;

    @Override
    public int getRootViewId() {
        return R.layout.frg_main;
    }

    @Override
    public void initView() {
        slideMenuLayout = (SlideMenuLayout) findViewById(R.id.mainSlideMenu);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            showFragment(AppLauncher.launchOnBoardingMain(), R.id.leftSlide);
        }
    }

    @Override
    public void initListener() {
        findViewById(R.id.fm_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getInstance().logoutSystem();
            }
        });
    }
}
