package com.jkb.support.expecption;

/**
 * Fragment已经被添加过的异常类
 * Created by JustKiddingBaby on 2016/12/20.
 */

public class HasBeenAddedException extends SupportException {

    public HasBeenAddedException(String error) {
        super("NotSupportException", String.format("%s has been added", error));
    }
}
