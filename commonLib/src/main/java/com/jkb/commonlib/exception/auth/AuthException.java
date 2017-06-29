package com.jkb.commonlib.exception.auth;


import com.jkb.support.utils.SLogUtils;

/**
 * 系统的异常类
 * 主要为系统的一些服务提供异常处理、防止返回结果无效等错误
 * Created by JustKiddingBaby on 2016/12/9.
 */

public class AuthException extends Exception {

    public AuthException(String operation, String error) {
        SLogUtils.e(this.getClass(), String.format("the operation %s is not  illegal ," +
                " the error is %s ,please check your operation！", operation, error));
    }
}
