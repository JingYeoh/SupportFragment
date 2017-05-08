package com.jkb.supportfragment.demo.entity.launch;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.widget.ImageView;

import com.jkb.commonlib.base.frame.BaseEntity;
import com.jkb.supportfragment.demo.BR;

/**
 * 启动页面：数据实体类
 * Created by yj on 2017/5/5.
 */

public class LaunchEntity extends BaseEntity {

    private static final long serialVersionUID = 966736689475971873L;

    @IdRes
    private int launchPicture;

    @Bindable
    public int getLaunchPicture() {
        return launchPicture;
    }

    public void setLaunchPicture(int launchPicture) {
        this.launchPicture = launchPicture;
        notifyPropertyChanged(BR.launchPicture);
    }

    @BindingAdapter("launchImg")
    public static void launchImage(ImageView imageView, @DrawableRes int launchImg) {
        imageView.setImageResource(launchImg);
    }
}