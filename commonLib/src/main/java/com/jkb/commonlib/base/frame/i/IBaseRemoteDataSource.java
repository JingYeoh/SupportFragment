package com.jkb.commonlib.base.frame.i;

import com.jkb.commonlib.base.frame.BaseModel;

/**
 * 数据仓库的基类
 * Created by yj on 2017/5/12.
 */

public class IBaseRemoteDataSource implements BaseModel {

    protected static IBaseRemoteDataSource sInstance;

    protected IBaseRemoteDataSource() {
    }

    @Override
    public void cacheExpired() {
    }

    @Override
    public void destroy() {
        sInstance = null;
    }
}
