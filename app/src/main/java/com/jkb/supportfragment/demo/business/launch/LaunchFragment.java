package com.jkb.supportfragment.demo.business.launch;

import android.os.Bundle;
import android.os.Message;

import com.jkb.commonlib.base.BaseFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.helper.AppLauncher;
import com.jkb.supportfragment.demo.R;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

/**
 * 启动页
 * Created by yj on 2017/5/4.
 */

public class LaunchFragment extends BaseFragment {

    public static LaunchFragment newInstance() {
        Bundle args = new Bundle();
        LaunchFragment fragment = new LaunchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.frg_launch;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * App初始化完成
     */
    @Subscriber(mode = ThreadMode.MAIN, tag = AppConfig.EventBusTAG.APP_INIT_COMPLECTED)
    public void onAppInitCompleted(Message message) {
        startFragment(AppLauncher.launchOnBoardingMain());
        close();
    }
}
