package com.jkb.supportfragment.demo.business.auth.register.model.local;

import android.content.Context;

import com.jkb.commonlib.base.frame.i.IBaseLocalDataSource;
import com.jkb.commonlib.db.entity.User;
import com.jkb.supportfragment.demo.business.auth.register.model.RegisterDataSource;
import com.jkb.supportfragment.demo.entity.auth.RegisterEntity;
import com.jkb.supportfragment.demo.net.entity.PictureAttribute;

/**
 * 注册的本地数据来源类
 * Created by yj on 2017/5/12.
 */

public class RegisterLocalDataSource extends IBaseLocalDataSource implements RegisterDataSource {

    private RegisterLocalDataSource(Context context) {
        super(context);
    }

    public static RegisterLocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            synchronized (IBaseLocalDataSource.class) {
                if (sInstance == null) {
                    sInstance = new RegisterLocalDataSource(context);
                }
            }
        }
        return (RegisterLocalDataSource) sInstance;
    }

    @Override
    public void uploadPicture(String path, LoadDataCallBack<PictureAttribute> callBack) {

    }

    @Override
    public void register(RegisterEntity entity, LoadDataCallBack<User> callBack) {

    }
}
