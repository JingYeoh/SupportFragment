package com.jkb.supportfragment.demo.business.auth.verCode.model.remote;

import com.jkb.supportfragment.demo.business.auth.verCode.model.VerCodeDataSource;
import com.jkb.supportfragment.demo.net.entity.VerCodeAttributes;

/**
 * 验证码的本地数据来源类
 * Created by yj on 2017/5/8.
 */

public class VerCodeRemoteDataSource implements VerCodeDataSource {

    private VerCodeRemoteDataSource() {
    }

    private static VerCodeRemoteDataSource sInstance;

    public static VerCodeRemoteDataSource getInstance() {
        if (sInstance == null) {
            synchronized (VerCodeRemoteDataSource.class) {
                if (sInstance == null) {
                    sInstance = new VerCodeRemoteDataSource();
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
    public void sendVerCode(String account, LoadDataCallBack<VerCodeAttributes> callBack) {

    }

    @Override
    public void identifyVerCodeWithAccount(String account, String verCode, LoadDataCallBack<Boolean> callBack) {

    }
}
