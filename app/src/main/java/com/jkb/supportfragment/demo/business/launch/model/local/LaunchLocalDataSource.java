package com.jkb.supportfragment.demo.business.launch.model.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.jkb.commonlib.config.AppConfig;
import com.jkb.supportfragment.demo.business.launch.model.LaunchDataSource;

/**
 * 启动页：数据仓库
 * Created by yj on 2017/5/5.
 */

public class LaunchLocalDataSource implements LaunchDataSource {

    private Context context;

    private LaunchLocalDataSource(Context context) {
        this.context = context;
    }

    private static LaunchLocalDataSource sInstance;

    public static LaunchLocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LaunchLocalDataSource.class) {
                if (sInstance == null) {
                    sInstance = new LaunchLocalDataSource(context);
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
    public void getLastUseVersion(LoadDataCallBack<String> callBack) {
        SharedPreferences sp = context.getSharedPreferences(AppConfig.SharedPreference.NAME_APP, 0);
        String version = sp.getString(AppConfig.SharedPreference.KEY_LASH_USE_VERSION, null);
        if (TextUtils.isEmpty(version)) {
            callBack.onDataNotAvailable("app is first use");
        } else {
            callBack.onDataLoaded(version);
        }
    }

    @Override
    public void updateUseVersion() {
        SharedPreferences sp = context.getSharedPreferences(AppConfig.SharedPreference.NAME_APP, 0);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(AppConfig.SharedPreference.KEY_LASH_USE_VERSION, getCurrentVersion());
        edit.apply();
    }

    /**
     * 返回当前版本号
     */
    private String getCurrentVersion() {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
