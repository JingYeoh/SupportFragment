package com.jkb.supportfragment.demo.business.launch;

import android.os.Bundle;
import android.os.Message;

import com.jkb.commonlib.base.ui.BaseFrameFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.helper.AppLauncher;
import com.jkb.commonlib.ui.annotation.SupportContent;
import com.jkb.commonlib.ui.annotation.SupportWindow;
import com.jkb.supportfragment.demo.R;
import com.jkb.supportfragment.demo.business.helper.RepertoryInject;
import com.jkb.supportfragment.demo.business.launch.contract.LaunchContract;
import com.jkb.supportfragment.demo.business.launch.presenter.LaunchPresenter;
import com.jkb.supportfragment.demo.databinding.FrgLaunchBinding;
import com.jkb.supportfragment.demo.entity.launch.LaunchEntity;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

/**
 * 启动页
 * Created by yj on 2017/5/4.
 */
@SupportWindow(fullScreen = true)
@SupportContent(contentViewId = R.layout.frg_launch)
public class LaunchFragment extends BaseFrameFragment<LaunchPresenter, FrgLaunchBinding> implements
        LaunchContract.View {

    public static LaunchFragment newInstance() {
        Bundle args = new Bundle();
        LaunchFragment fragment = new LaunchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initPresenter() {
        new LaunchPresenter(this, RepertoryInject.provideLaunchDR(mContext));
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
        getPresenter().start();
    }

    @Override
    public void setLaunchEntity(LaunchEntity entity) {
        getBinding().setLaunch(entity);
    }

    @Override
    public void launchOnBoarding() {
        startFragment(AppLauncher.launchOnBoardingMain());
        close();
    }

    @Override
    public void launchMain() {
        startFragment(AppLauncher.launchAppMain());
        close();
    }

    @Override
    public void launchAccount() {
        startFragment(AppLauncher.launchAccount());
        close();
    }

    @Override
    public void setPresenter(LaunchContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
