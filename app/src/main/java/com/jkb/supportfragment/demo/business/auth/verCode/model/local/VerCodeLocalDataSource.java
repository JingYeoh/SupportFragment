package com.jkb.supportfragment.demo.business.auth.verCode.model.local;

import android.content.Context;

import com.jkb.supportfragment.demo.business.auth.verCode.model.VerCodeDataSource;

/**
 * 验证码的本地数据来源类
 * Created by yj on 2017/5/8.
 */

public class VerCodeLocalDataSource implements VerCodeDataSource {

    private Context context;

    private VerCodeLocalDataSource(Context context) {
        this.context = context;
    }

    private static VerCodeLocalDataSource sInstance;

    public static VerCodeLocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            synchronized (VerCodeLocalDataSource.class) {
                if (sInstance == null) {
                    sInstance = new VerCodeLocalDataSource(context);
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
        context = null;
    }

    @Override
    public void sendVerCode(String account, LoadDataCallBack<String> callBack) {

    }

    @Override
    public void identifyVerCodeWithAccount(String account, String verCode, LoadDataCallBack<Boolean> callBack) {

    }
}
