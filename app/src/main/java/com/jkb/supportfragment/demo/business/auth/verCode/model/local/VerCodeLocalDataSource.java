package com.jkb.supportfragment.demo.business.auth.verCode.model.local;

import android.content.Context;

import com.jkb.supportfragment.demo.business.auth.verCode.model.VerCodeDataSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 验证码的本地数据来源类
 * Created by yj on 2017/5/8.
 */

public class VerCodeLocalDataSource implements VerCodeDataSource {

    private Context context;
    private Map<String, String> mAccountVerCodes;

    private VerCodeLocalDataSource(Context context) {
        this.context = context;
        mAccountVerCodes = new HashMap<>();
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
        mAccountVerCodes.clear();
    }

    @Override
    public void destroy() {
        sInstance = null;
        context = null;
        mAccountVerCodes.clear();
    }

    @Override
    public void sendVerCode(String account, LoadDataCallBack<String> callBack) {
        //随机生成验证码
        Random random = new Random();
        int next = random.nextInt(8999) + 1000;
        mAccountVerCodes.put(account, String.valueOf(next));
        callBack.onDataLoaded(String.valueOf(next));
    }

    @Override
    public void identifyVerCodeWithAccount(String account, String verCode, LoadDataCallBack<Boolean> callBack) {
        String code = mAccountVerCodes.get(account);
        if (verCode.equals(code)) {
            callBack.onDataLoaded(true);
        } else {
            callBack.onDataNotAvailable("验证码错误");
        }
    }
}
