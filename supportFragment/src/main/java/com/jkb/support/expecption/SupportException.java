package com.jkb.support.expecption;

/**
 * 单Activity+多Fragment架构的基类
 * Created by JustKiddingBaby on 2016/12/20.
 */

public class SupportException extends RuntimeException {

    public SupportException(String action, String error) {
        super(String.format("the action is %s ,and error is %s \n,please check your code",
                action, error));
    }
}
