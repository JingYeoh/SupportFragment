package com.jkb.supportfragment.demo.business.auth.register.model;

import com.jkb.commonlib.base.frame.BaseModel;
import com.jkb.commonlib.db.entity.User;
import com.jkb.supportfragment.demo.entity.auth.RegisterEntity;
import com.jkb.supportfragment.demo.net.entity.PictureAttribute;

/**
 * 注册的数据来源类
 * Created by yj on 2017/5/12.
 */

public interface RegisterDataSource extends BaseModel {

    /**
     * 上传图片
     */
    void uploadPicture(String path, LoadDataCallBack<PictureAttribute> callBack);

    /**
     * 注册
     */
    void register(RegisterEntity entity, LoadDataCallBack<User> callBack);
}
