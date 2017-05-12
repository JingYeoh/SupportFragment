package com.jkb.supportfragment.demo.business.auth.register.model;

import com.jkb.commonlib.base.frame.i.IBaseDataRepertory;
import com.jkb.commonlib.db.entity.User;
import com.jkb.supportfragment.demo.entity.auth.RegisterEntity;
import com.jkb.supportfragment.demo.net.entity.PictureAttribute;

/**
 * 注册：数据仓库
 * Created by yj on 2017/5/12.
 */

public class RegisterDataRepertory extends IBaseDataRepertory<RegisterDataSource> implements RegisterDataSource {

    private RegisterDataRepertory(RegisterDataSource localDataSource, RegisterDataSource remoteDataSource) {
        super(localDataSource, remoteDataSource);
    }

    public static RegisterDataRepertory getInstance(RegisterDataSource localDataSource, RegisterDataSource
            remoteDataSource) {
        if (sInstance == null) {
            synchronized (IBaseDataRepertory.class) {
                if (sInstance == null) {
                    sInstance = new RegisterDataRepertory(localDataSource, remoteDataSource);
                }
            }
        }
        return (RegisterDataRepertory) sInstance;
    }

    @Override
    public void uploadPicture(String path, LoadDataCallBack<PictureAttribute> callBack) {
        remoteDataSource.uploadPicture(path, callBack);
    }

    @Override
    public void register(RegisterEntity entity, LoadDataCallBack<User> callBack) {
        localDataSource.register(entity, callBack);
    }
}
