package com.jkb.supportfragment.demo.entity.auth;

import android.databinding.Bindable;

import com.jkb.commonlib.base.frame.BaseEntity;
import com.jkb.supportfragment.demo.BR;

import java.util.ArrayList;
import java.util.List;

/**
 * 帐号的数据实体类
 * Created by yj on 2017/5/5.
 */

public class AccountEntity extends BaseEntity {
    private static final long serialVersionUID = 3087381555459043910L;

    private String account;
    private String areaCode;
    private List<AreaCodeEntity> areaCodes = new ArrayList<>();

    @Bindable
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
        notifyPropertyChanged(BR.account);
    }

    @Bindable
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        notifyPropertyChanged(BR.areaCode);
    }

    public List<AreaCodeEntity> getAreaCodes() {
        return areaCodes;
    }

    public void setAreaCodes(List<AreaCodeEntity> areaCodes) {
        this.areaCodes = areaCodes;
    }
}
