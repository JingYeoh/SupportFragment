package com.jkb.supportfragment.demo.net.entity;

import com.jkb.commonlib.base.data.BaseAttribute;

/**
 * 验证码的属性类
 * Created by yj on 2017/6/1.
 */

public class VerCodeAttributes extends BaseAttribute {
    private static final long serialVersionUID = 5315326545923132494L;

    public static final int LOGIN = 1001;
    public static final int REGISTER = 1002;
    public static final int PASSWORD_RESET = 1003;
    public static final int PASSWORD_FORGET = 1004;

    private String code;//验证码
    private int action;//动作
    private int userId;//用户id

    public VerCodeAttributes(String code, int action, int userId) {
        this.code = code;
        this.action = action;
        this.userId = userId;
    }

    public VerCodeAttributes() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
