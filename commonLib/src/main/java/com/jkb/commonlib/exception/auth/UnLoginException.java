package com.jkb.commonlib.exception.auth;


import com.jkb.commonlib.app.AppManager;

/**
 * 系统未登录的异常
 * Created by JustKiddingBaby on 2016/12/10.
 */

public class UnLoginException extends AuthException {

    private static final long serialVersionUID = -5774384528532869354L;

    public UnLoginException(String error) {
        //设置退出登录
        super("system is logout status", error);
        AppManager.getInstance().logoutSystem();
    }
}
