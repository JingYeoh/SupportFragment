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
                build(AppConfig.RouterPath.ONBOARDING_PLACEHOLDER)
                .withInt(AppConfig.KeyBundle.ONBOARDING_TYPE, onBoardType).navigation();
    }

    /**
     * 启动输入帐号页面
     */
    public static SupportFragment launchAccount() {
        return (SupportFragment) ARouter.getInstance().
                build(AppConfig.RouterPath.AUTH_ACCOUNT).navigation();
    }

    /**
     * 启动登录页面
     */
    public static SupportFragment launchLogin() {
        return (SupportFragment) ARouter.getInstance().
                build(AppConfig.RouterPath.AUTH_LOGIN).navigation();
    }
}