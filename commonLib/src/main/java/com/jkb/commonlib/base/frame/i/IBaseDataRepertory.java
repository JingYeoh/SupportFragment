package com.jkb.commonlib.base.frame.i;

import com.jkb.commonlib.base.frame.BaseModel;

/**
 * 数据仓库的基类
 * Created by yj on 2017/5/12.
 */

public class IBaseDataRepertory<M extends BaseModel> implements BaseModel {

    protected M localDataSource;
    protected M remoteDataSource;

    protected static IBaseDataRepertory sInstance;

    protected IBaseDataRepertory(BaseModel localDataSource, BaseModel remoteDataSource) {
        this.localDataSource = (M) localDataSource;
        this.remoteDataSource = (M) remoteDataSource;
    }

    @Override
    public void cacheExpired() {
        if (localDataSource != null) localDataSource.cacheExpired();
        if (remoteDataSource != null) remoteDataSource.cacheExpired();
    }

    @Override
    public void destroy() {
        if (localDataSource != null) localDataSource.destroy();
        if (remoteDataSource != null) remoteDataSource.destroy();
        sInstance = null;
    }
}
