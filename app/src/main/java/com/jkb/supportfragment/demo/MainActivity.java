package com.jkb.supportfragment.demo;

import android.os.Bundle;
import android.os.Message;

import com.jkb.commonlib.base.ui.BaseActivity;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.supportfragment.demo.business.launch.LaunchFragment;

import org.simple.eventbus.EventBus;

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
        EventBus.getDefault().register(this);
        if (savedInstanceState == null) {
            startFragment(LaunchFragment.newInstance());
        }
        EventBus.getDefault().post(Message.obtain(), AppConfig.EventBusTAG.APP_INIT);
    }

    @Override
    public void initListener() {

    }

    @Override
    public int getFragmentContentId() {
        return R.id.mainFrameContent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void close() {
        super.close();
        System.exit(0);
    }
}
