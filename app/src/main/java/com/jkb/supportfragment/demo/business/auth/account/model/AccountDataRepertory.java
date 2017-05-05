package com.jkb.supportfragment.demo.business.auth.account.model;

import com.jkb.supportfragment.demo.entity.auth.AreaCodeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 帐号：远程数据来源
 * Created by yj on 2017/5/5.
 */

public class AccountDataRepertory implements AccountDataSource {

    private AccountDataSource localDataSource;
    private AccountDataSource remoteDataSource;

    private List<AreaCodeEntity> cacheAreaCodeEntity;

    private AccountDataRepertory(AccountDataSource localDataSource, AccountDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;

        cacheAreaCodeEntity = new ArrayList<>();
    }

    private static AccountDataRepertory sInstance;

    public static AccountDataRepertory getInstance(AccountDataSource localDataSource,
                                                   AccountDataSource remoteDataSource) {
        if (sInstance == null) {
            synchronized (AccountDataRepertory.class) {
                if (sInstance == null) {
                    sInstance = new AccountDataRepertory(localDataSource, remoteDataSource);
                }
            }
        }
        return sInstance;
    }

    @Override
    public void cacheExpired() {
        if (localDataSource != null) localDataSource.cacheExpired();
        if (remoteDataSource != null) remoteDataSource.cacheExpired();
        cacheAreaCodeEntity.clear();
    }

    @Override
    public void destroy() {
        if (localDataSource != null) localDataSource.destroy();
        if (remoteDataSource != null) remoteDataSource.destroy();
        sInstance = null;
        cacheAreaCodeEntity = null;
    }

    @Override
    public void identifyAccount(String account, LoadDataCallBack<Boolean> callBack) {
        remoteDataSource.identifyAccount(account, callBack);
    }

    @Override
    public void getAreaCodes(final LoadDataCallBack<List<AreaCodeEntity>> callBack) {
        if (cacheAreaCodeEntity != null && cacheAreaCodeEntity.size() > 0) {
            callBack.onDataLoaded(cacheAreaCodeEntity);
            return;
        }
        localDataSource.getAreaCodes(new LoadDataCallBack<List<AreaCodeEntity>>() {
            @Override
            public void onDataLoaded(List<AreaCodeEntity> data) {
                cacheAreaCodeEntity = new ArrayList<>();
                cacheAreaCodeEntity.addAll(data);
                callBack.onDataLoaded(cacheAreaCodeEntity);
            }

            @Override
            public void onDataNotAvailable(String errData) {
                callBack.onDataNotAvailable(errData);
            }
        });
    }
}
