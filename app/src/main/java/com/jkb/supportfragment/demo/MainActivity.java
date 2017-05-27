package com.jkb.supportfragment.demo;

import android.os.Bundle;
import android.os.Message;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.app.AppManager;
import com.jkb.commonlib.base.ui.BaseActivity;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.helper.AppLauncher;
import com.jkb.supportfragment.demo.business.launch.LaunchFragment;

import org.simple.eventbus.EventBus;

import java.util.Observable;
import java.util.Observer;

/**
 * 单Activity+多Fragment架构的主Activity
 */
public class MainActivity extends BaseActivity implements Observer {

    @Override
    public int getRootViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        if (savedInstanceState == null) {
            startFragment(LaunchFragment.newInstance());
        }
        EventBus.getDefault().post(Message.obtain(), AppConfig.EventBusTAG.APP_INIT);
    }

    @Override
    public void initListener() {
        AppManager.getInstance().addObserver(this);
    }

    @Override
    public int getFragmentContentId() {
        return R.id.mainFrameContent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        AppManager.getInstance().deleteObserver(this);
    }

    @Override
    public void close() {
        super.close();
        System.exit(0);
    }

    @Override
    public void update(Observable o, Object arg) {
        int systemStatusCode = (int) arg;
        switch (systemStatusCode) {
            case AppConfig.AppStatus.LOGOUT:
            case AppConfig.AppStatus.ERROR:
                clearFragments();
                startFragment(AppLauncher.launchAccount());
                break;
        }
    }
}
