package com.jkb.commonlib.helper;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jkb.commonlib.base.ui.BaseDialogFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.support.ui.SupportFragment;

import java.io.Serializable;

/**
 * App的启动帮助类
 */

public class AppLauncher {

    /**
     * 启动新手上路主页
     */
    public static SupportFragment launchOnBoardingMain() {
        return (SupportFragment) ARouter.getInstance().
                build(AppConfig.RouterPath.ONBOARDING_CONTENT)
                .navigation();
    }

    /**
     * 启动新手上路占位
     */
    public static SupportFragment launchOnBoardingPlaceHolder(int onBoardType) {
        return (SupportFragment) ARouter.getInstance().
                build(AppConfig.RouterPath.ONBOARDING_PLACEHOLDER)
                .withInt(AppConfig.KeyBundle.ONBOARDING_TYPE, onBoardType)
                .navigation();
    }

    /**
     * 启动输入帐号页面
     */
    public static SupportFragment launchAccount() {
        return (SupportFragment) ARouter.getInstance().
                build(AppConfig.RouterPath.AUTH_ACCOUNT)
                .navigation();
    }

    /**
     * 启动登录页面
     */
    public static SupportFragment launchLogin(String account) {
        return (SupportFragment) ARouter.getInstance()
                .build(AppConfig.RouterPath.AUTH_LOGIN)
                .withString(AppConfig.KeyBundle.ACCOUNT, account)
                .navigation();
    }

    /**
     * 启动地区编码页面
     *
     * @param value ArrayList<AreaCodeEntity>数据
     */
    public static BaseDialogFragment launchAreaCode(Serializable value) {
        return (BaseDialogFragment) ARouter.getInstance()
                .build(AppConfig.RouterPath.AUTH_AREA_CODE)
                .withSerializable(AppConfig.KeyBundle.AREACODE, value)
                .navigation();
    }

    /**
     * 启动发送验证码页面
     */
    public static SupportFragment launchVerCode(String account, int verCodeType) {
        return (SupportFragment) ARouter.getInstance()
                .build(AppConfig.RouterPath.AUTH_VERCODE)
                .withString(AppConfig.KeyBundle.ACCOUNT, account)
                .withInt(AppConfig.KeyBundle.VERCODE_TYPE, verCodeType)
                .navigation();
    }

    /**
     * 启动注册页面
     */
    public static SupportFragment launchRegister(String account) {
        return (SupportFragment) ARouter.getInstance()
                .build(AppConfig.RouterPath.AUTH_REGISTER)
                .withString(AppConfig.KeyBundle.ACCOUNT, account)
                .navigation();
    }

    /**
     * 启动App首页
     */
    public static SupportFragment launchAppMain() {
        return (SupportFragment) ARouter.getInstance()
                .build(AppConfig.RouterPath.APP_MAIN)
                .navigation();
    }

    /**
     * 启动图片选择器
     */
    public static SupportFragment launchPhotoSelector() {
        return (SupportFragment) ARouter.getInstance()
                .build(AppConfig.RouterPath.PHOTO_SELECTOR)
                .navigation();
    }

    /**
     * 启动右滑菜单
     */
    public static SupportFragment launchSlideMenuRight() {
        return (SupportFragment) ARouter.getInstance()
                .build(AppConfig.RouterPath.APP_MAIN_SLIDE_RIGHT)
                .navigation();
    }

    /**
     * 启动左滑菜单
     */
    public static SupportFragment launchSlideMenuLeft() {
        return (SupportFragment) ARouter.getInstance()
                .build(AppConfig.RouterPath.APP_MAIN_SLIDE_LEFT)
                .navigation();
    }

    /**
     * 启动好友列表
     */
    public static SupportFragment launchUserFriendsList() {
        return (SupportFragment) ARouter.getInstance()
                .build(AppConfig.RouterPath.USER_FRIENDS_LIST)
                .navigation();
    }

    /**
     * 启动聊天列表
     */
    public static SupportFragment launchUserChatList() {
        return (SupportFragment) ARouter.getInstance()
                .build(AppConfig.RouterPath.USER_CHAT_LIST)
                .navigation();
    }

    /**
     * 启动雷达探测
     */
    public static SupportFragment launchAppRadarSearch() {
        return (SupportFragment) ARouter.getInstance()
                .build(AppConfig.RouterPath.APP_MAIN_RADAR)
                .navigation();
    }
}