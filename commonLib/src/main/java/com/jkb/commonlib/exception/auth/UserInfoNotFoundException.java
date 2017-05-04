package com.jkb.commonlib.exception.auth;


/**
 * 用户信息未发现的异常
 * Created by JustKiddingBaby on 2016/12/9.
 */

public class UserInfoNotFoundException extends AuthException {

    public UserInfoNotFoundException(String error) {
        super("user info not found", error);
    }
}
