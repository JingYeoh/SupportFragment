package com.jkb.support.expecption;

/**
 * 非继承的SupportFragment的异常
 * Created by JustKiddingBaby on 2016/12/8.
 */

public class NotSupportException extends SupportException {

    public NotSupportException(String fragmentTag) {
        super("NotSupportException",
                String.format("%s is not extends SupportFragment", fragmentTag));
    }
}
