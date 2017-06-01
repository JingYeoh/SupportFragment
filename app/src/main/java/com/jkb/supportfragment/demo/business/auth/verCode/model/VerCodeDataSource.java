package com.jkb.supportfragment.demo.business.auth.verCode.model;

import com.jkb.commonlib.base.frame.BaseModel;
import com.jkb.supportfragment.demo.net.entity.VerCodeAttributes;

/**
 * 发送验证码：DataSource
 * Created by yj on 2017/5/8.
 */

public interface VerCodeDataSource extends BaseModel {

    /**
     * 发送验证码
     */
    void sendVerCode(String account, LoadDataCallBack<VerCodeAttributes> callBack);

    /**
     * 使用帐号和验证码验证
     */
    void identifyVerCodeWithAccount(String account, String verCode, LoadDataCallBack<Boolean> callBack);
}
