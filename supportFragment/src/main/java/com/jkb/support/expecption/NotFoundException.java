package com.jkb.support.expecption;

/**
 * 没有发现Fragment的异常
 * Created by JustKiddingBaby on 2016/12/20.
 */

public class NotFoundException extends SupportException {

    public NotFoundException(String error) {
        super("NotFoundException",
                String.format("%s", error));
    }
}
