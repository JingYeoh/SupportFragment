package com.jkb.commonlib.helper;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.support.ui.SupportFragment;

/**
 * App的启动帮助类
 */

public class AppLauncher {

    /**
     * 启动新手上路主页
     */
    public static SupportFragment launchOnBoardingMain() {
        return (SupportFragment) ARouter.getInstance().
                build(AppConfig.RouterPath.ONBOARDING_CONTENT).navigation();
    }

    /**
     * 启动新手上路占位
     */
    public static SupportFragment launchOnBoardingPlaceHolder(int onBoardType) {
        return (SupportFragment) ARouter.getInstance().
                build(AppConfig.RouterPath.ONBOARDING_CONTENT)
                .withInt(AppConfig.KeyBundle.ONBOARDING_TYPE, onBoardType).navigation();
    }
}
