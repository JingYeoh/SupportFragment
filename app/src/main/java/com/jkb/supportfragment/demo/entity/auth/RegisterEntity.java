package com.jkb.supportfragment.demo.entity.auth;

import android.databinding.Bindable;

import com.jkb.commonlib.base.frame.BaseEntity;
import com.jkb.commonlib.utils.StringUtils;
import com.jkb.supportfragment.demo.BR;

/**
 * 注册的数据实体
 * Created by yj on 2017/5/12.
 */

public class RegisterEntity extends BaseEntity {
    private static final long serialVersionUID = -596999730725020500L;

    private String account;
    private String name;
    private String avatarUrl;
    private String password;
    private String sex;
    private String birthday;

    @Bindable
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
        notifyPropertyChanged(BR.account);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        notifyPropertyChanged(BR.avatarUrl);
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
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        notifyPropertyChanged(BR.sex);
    }

    @Bindable
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 是否允许注册
     */
    @Bindable
    public boolean isAllowRegister() {
        return !StringUtils.hasEmpty(getAccount(), getAvatarUrl(), getBirthday(),
                getName(), getPassword(), getSex());
    }
}
