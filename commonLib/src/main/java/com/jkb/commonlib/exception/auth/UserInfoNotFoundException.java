package com.jkb.commonlib.exception.auth;


import com.jkb.commonlib.app.AppManager;

/**
 * 用户信息未发现的异常
 * Created by JustKiddingBaby on 2016/12/9.
 */

public class UserInfoNotFoundException extends AuthException {

    private static final long serialVersionUID = -8762521982668754249L;

    public UserInfoNotFoundException(String error) {
        super("user info not found", error);
        AppManager.getInstance().onSystemError();
    }
}
