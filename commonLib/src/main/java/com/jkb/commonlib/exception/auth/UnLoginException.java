package com.jkb.commonlib.exception.auth;


/**
 * 系统未登录的异常
 * Created by JustKiddingBaby on 2016/12/10.
 */

public class UnLoginException extends AuthException {

    public UnLoginException(String error) {
        //设置退出登录
        super("system is logout status", error);
    }
}
