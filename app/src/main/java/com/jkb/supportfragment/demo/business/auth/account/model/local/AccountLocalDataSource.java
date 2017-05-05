package com.jkb.supportfragment.demo.business.auth.account.model.local;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jkb.commonlib.utils.StringUtils;
import com.jkb.supportfragment.demo.R;
import com.jkb.supportfragment.demo.business.auth.account.model.AccountDataSource;
import com.jkb.supportfragment.demo.entity.auth.AreaCodeEntity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 帐号：远程数据来源
 * Created by yj on 2017/5/5.
 */

public class AccountLocalDataSource implements AccountDataSource {

    private Context context;

    private AccountLocalDataSource(Context context) {
        this.context = context;
    }

    private static AccountLocalDataSource sInstance;

    public static AccountLocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            synchronized (AccountLocalDataSource.class) {
                if (sInstance == null) {
                    sInstance = new AccountLocalDataSource(context);
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
        InputStream is = context.getResources().openRawResource(R.raw.countries);
        try {
            String content = StringUtils.InputStreamToString(is);
            if (TextUtils.isEmpty(content)) {
                callBack.onDataNotAvailable("invalid data");
                return;
            }
            ArrayList<AreaCodeEntity> areaCodeEntities;
            Gson gson = new Gson();
            areaCodeEntities = gson.fromJson(content, new TypeToken<List<AreaCodeEntity>>() {
            }.getType());
            if (areaCodeEntities == null) {
                callBack.onDataNotAvailable("invalid data");
            } else {
                callBack.onDataLoaded(areaCodeEntities);
            }
        } catch (Exception e) {
            callBack.onDataNotAvailable("invalid data");
        }
    }
}
