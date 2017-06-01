package com.jkb.supportfragment.demo.business.auth.verCode.model;

import com.jkb.supportfragment.demo.net.entity.VerCodeAttributes;

/**
 * 验证码的本地数据来源类
 * Created by yj on 2017/5/8.
 */

public class VerCodeDataRepertory implements VerCodeDataSource {

    private VerCodeDataSource localDataSource;
    private VerCodeDataSource remoteDataSource;

    private VerCodeDataRepertory(VerCodeDataSource localDataSource, VerCodeDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    private static VerCodeDataRepertory sInstance;

    public static VerCodeDataRepertory getInstance(VerCodeDataSource localDataSource,
                                                   VerCodeDataSource remoteDataSource) {
        if (sInstance == null) {
            synchronized (VerCodeDataRepertory.class) {
                if (sInstance == null) {
                    sInstance = new VerCodeDataRepertory(localDataSource, remoteDataSource);
                }
            }
        }
        return sInstance;
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

    @Override
    public void sendVerCode(String account, LoadDataCallBack<VerCodeAttributes> callBack) {
        localDataSource.sendVerCode(account, callBack);
    }

    @Override
    public void identifyVerCodeWithAccount(String account, String verCode, LoadDataCallBack<Boolean> callBack) {
        localDataSource.identifyVerCodeWithAccount(account, verCode, callBack);
    }
}
