package com.jkb.supportfragment.demo.business.auth.register.model.remote;

import com.jkb.commonlib.base.frame.i.IBaseLocalDataSource;
import com.jkb.commonlib.base.frame.i.IBaseRemoteDataSource;
import com.jkb.commonlib.db.entity.User;
import com.jkb.supportfragment.demo.business.auth.register.model.RegisterDataSource;
import com.jkb.supportfragment.demo.entity.auth.RegisterEntity;
import com.jkb.supportfragment.demo.net.entity.PictureAttribute;

/**
 * 注册的本地数据来源类
 * Created by yj on 2017/5/12.
 */

public class RegisterRemoteDataSource extends IBaseRemoteDataSource implements RegisterDataSource {

    private RegisterRemoteDataSource() {
    }

    public static RegisterRemoteDataSource getInstance() {
        if (sInstance == null) {
            synchronized (IBaseLocalDataSource.class) {
                if (sInstance == null) {
                    sInstance = new RegisterRemoteDataSource();
                }
            }
        }
        return (RegisterRemoteDataSource) sInstance;
    }

    @Override
    public void uploadPicture(String path, LoadDataCallBack<PictureAttribute> callBack) {

    }

    @Override
    public void register(RegisterEntity entity, LoadDataCallBack<User> callBack) {

    }
}
