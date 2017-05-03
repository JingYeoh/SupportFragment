package com.jkb.support.expecption;

/**
 * Fragment没有被添加过的异常类
 * Created by JustKiddingBaby on 2016/12/20.
 */

public class NotAddedException extends SupportException {

    public NotAddedException(String error) {
        super("NotSupportException", String.format("%s is not added", error));
    }
}
