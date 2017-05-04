package com.jkb.supportfragment.demo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 该demo的Application类
 * Created by yj on 2017/5/3.
 */

public class SupportApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
