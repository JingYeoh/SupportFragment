package com.jkb.supportfragment.demo.business.main;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.base.ui.BaseFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.helper.AppLauncher;
import com.jkb.commonlib.ui.annotation.SupportWindow;
import com.jkb.supportfragment.demo.R;
import com.jkb.supportfragment.demo.view.slidemenu.SlideMenuLayout;

/**
 * App主页
 * Created by yj on 2017/5/12.
 */
@SupportWindow(immersiveStatus = true)
@Route(path = AppConfig.RouterPath.APP_MAIN)
public class MainFragment extends BaseFragment implements View.OnClickListener {

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
            showFragment(AppLauncher.launchSlideMenuRight(), R.id.leftSlide);
            showFragment(AppLauncher.launchSlideMenuRight(), R.id.rightSlide);
        }
    }

    @Override
    public void initListener() {
        findViewById(R.id.fm_leftMenu).setOnClickListener(this);
        findViewById(R.id.fm_rightMenu).setOnClickListener(this);
        findViewById(R.id.fm_slide_content).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fm_leftMenu:
                slideMenuLayout.toggleLeftSlide();
                break;
            case R.id.fm_rightMenu:
                slideMenuLayout.toggleRightSlide();
                break;
            case R.id.fm_slide_content:
                slideMenuLayout.closeLeftSlide();
                slideMenuLayout.closeRightSlide();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (slideMenuLayout.isLeftSlideOpen() || slideMenuLayout.isRightSlideOpen()) {
            slideMenuLayout.closeLeftSlide();
            slideMenuLayout.closeRightSlide();
        } else {
            mActivity.close();
        }
    }
}
