package com.jkb.supportfragment.demo.entity.auth;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.jkb.commonlib.base.frame.BaseEntity;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.loader.ImageLoaderHelper;
import com.jkb.commonlib.utils.StringUtils;
import com.jkb.support.utils.LogUtils;
import com.jkb.supportfragment.demo.BR;
import com.jkb.supportfragment.demo.R;

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
    private String sex = "男";
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
        notifyPropertyChanged(BR.birthday);
    }

    /**
     * 切换性别
     */
    public void changeSex() {
        if (sex.equals("男")) {
            sex = "女";
        } else {
            sex = "男";
        }
    }

    @BindingAdapter({"userAvatar"})
    public static void loadImage(ImageView view, String avatarUrl) {
        if (TextUtils.isEmpty(avatarUrl)) {
            view.setImageResource(R.drawable.ic_placeholder_profile);
        } else {
            ImageLoaderHelper.displayImage(avatarUrl, R.drawable.ic_placeholder_profile, view);
        }
    }
}
