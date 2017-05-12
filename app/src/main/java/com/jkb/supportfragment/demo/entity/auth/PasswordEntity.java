package com.jkb.supportfragment.demo.entity.auth;

import android.databinding.Bindable;

import com.jkb.commonlib.base.frame.BaseEntity;
import com.jkb.supportfragment.demo.BR;

/**
 * 密码的数据实体类
 * Created by yj on 2017/5/12.
 */

public class PasswordEntity extends BaseEntity {
    private static final long serialVersionUID = -2009504217807449282L;

    private String account;
    private String password;
    private String oldPassword;
    private String newPassword;

    @Bindable
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
        notifyPropertyChanged(BR.account);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        notifyPropertyChanged(BR.oldPassword);
    }

    @Bindable
    public String getNewPassword() {
        return newPassword;
    }

    @Bindable
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        notifyPropertyChanged(BR.newPassword);
    }
}
