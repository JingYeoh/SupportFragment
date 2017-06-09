package com.jkb.supportfragment.demo.entity.main;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.jkb.commonlib.base.frame.BaseEntity;
import com.jkb.commonlib.loader.ImageLoaderHelper;
import com.jkb.supportfragment.demo.BR;
import com.jkb.supportfragment.demo.R;

/**
 * 雷达：数据实体类
 * Created by yangjing on 17-6-9.
 */
public class RadarEntity extends BaseEntity {
    private static final long serialVersionUID = 3067272132125850790L;

    private String avatar;

    public RadarEntity(String avatar) {
        this.avatar = avatar;
    }

    public RadarEntity() {
    }

    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
    }

    /**
     * 添加旋转动画
     */
    @BindingAdapter({"rotateAnim"})
    public static void addRotateAnimation(final View view, boolean isRotate) {
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.anim_radar_rotate);
        animation.setInterpolator(new LinearInterpolator());
        view.setAnimation(animation);
    }

    @BindingAdapter({"loadRadarUserAvatar"})
    public static void loadImage(ImageView view, String avatarUrl) {
        if (TextUtils.isEmpty(avatarUrl)) {
            view.setImageResource(R.drawable.ic_launcher);
        } else {
            ImageLoaderHelper.displayImage(avatarUrl, R.drawable.ic_launcher, view);
        }
    }
}