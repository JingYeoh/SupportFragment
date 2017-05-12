package com.jkb.commonlib.base.frame.i;

import android.content.Context;

import com.jkb.commonlib.base.frame.BaseModel;

/**
 * 数据仓库的基类
 * Created by yj on 2017/5/12.
 */

public class IBaseLocalDataSource implements BaseModel {

    protected Context context;

    protected static IBaseLocalDataSource sInstance;

    protected IBaseLocalDataSource(Context context) {
        this.context = context;
    }

    @Override
    public void cacheExpired() {
    }

    @Override
    public void destroy() {
        sInstance = null;
        context = null;
    }
}
