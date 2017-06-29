package com.jkb.supportfragment.demo.entity.auth;

import android.databinding.Bindable;

import com.jkb.commonlib.base.frame.BaseEntity;
import com.jkb.support.utils.SLogUtils;
import com.jkb.supportfragment.demo.BR;

/**
 * 验证码的数据实体类
 * Created by yj on 2017/5/8.
 */

public class VerCodeEntity extends BaseEntity {

    private static final long serialVersionUID = -4813127647080390124L;

    public static final int VERCODE_COUNT = 4;//验证码位数
    private String phoneNumber;
    private String verCode;
    private int sendCount = 0;

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);
    }

    @Bindable
    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
        notifyPropertyChanged(BR.verCode);
        SLogUtils.d(this, "verCode=" + verCode);
    }

    @Bindable
    public int getSendCount() {
        return sendCount;
    }

    public void setSendCount(int sendCount) {
        this.sendCount = sendCount;
        notifyPropertyChanged(BR.sendCount);
    }
}
