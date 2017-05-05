package com.jkb.supportfragment.demo.entity.auth;

import android.databinding.Bindable;

import com.jkb.commonlib.base.frame.BaseEntity;
import com.jkb.supportfragment.demo.BR;

/**
 * 地区编号的实体类
 * Created by yj on 2017/5/5.
 */

public class AreaCodeEntity extends BaseEntity {

    private static final long serialVersionUID = -4335822911173711806L;

    private String id;
    private String code;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        notifyPropertyChanged(BR.code);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
