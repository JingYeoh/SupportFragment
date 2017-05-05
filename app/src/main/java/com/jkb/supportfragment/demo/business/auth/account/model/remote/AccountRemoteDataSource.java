package com.jkb.supportfragment.demo.business.auth.account.model.remote;

import com.jkb.commonlib.base.frame.BaseModel;
import com.jkb.supportfragment.demo.business.auth.account.model.AccountDataSource;
import com.jkb.supportfragment.demo.entity.auth.AreaCodeEntity;

import java.util.List;

/**
 * 帐号：远程数据来源
 * Created by yj on 2017/5/5.
 */

public class AccountRemoteDataSource implements AccountDataSource {

    private AccountRemoteDataSource() {
    }

    private static AccountRemoteDataSource sInstance;

    public static AccountRemoteDataSource getInstance() {
        if (sInstance == null) {
            synchronized (AccountRemoteDataSource.class) {
                if (sInstance == null) {
                    sInstance = new AccountRemoteDataSource();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void cacheExpired() {
    }

    @Override
    public void destroy() {
        sInstance = null;
    }

    @Override
    public void identifyAccount(String account, LoadDataCallBack<Boolean> callBack) {

    }

    @Override
    public void getAreaCodes(LoadDataCallBack<List<AreaCodeEntity>> callBack) {

    }
}
