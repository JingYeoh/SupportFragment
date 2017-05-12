package com.jkb.supportfragment.demo.net.entity;

import com.jkb.commonlib.base.data.BaseAttribute;

/**
 * 图片的属性类
 * Created by yj on 2017/5/12.
 */

public class PictureAttribute extends BaseAttribute {

    private static final long serialVersionUID = 8228425649508848812L;

    private String photoPath;

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
