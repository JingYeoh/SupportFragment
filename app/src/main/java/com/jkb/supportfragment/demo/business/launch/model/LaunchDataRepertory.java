package com.jkb.supportfragment.demo.business.launch.model;

/**
 * 启动页：数据仓库
 * Created by yj on 2017/5/5.
 */

public class LaunchDataRepertory implements LaunchDataSource {

    private LaunchDataSource localDataSource;

    private LaunchDataRepertory(LaunchDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    private static LaunchDataRepertory sInstance;

    public static LaunchDataRepertory getInstance(LaunchDataSource localDataSource) {
        if (sInstance == null) {
            synchronized (LaunchDataRepertory.class) {
                if (sInstance == null) {
                    sInstance = new LaunchDataRepertory(localDataSource);
                }
            }
        }
        return sInstance;
    }

    @Override
    public void cacheExpired() {
        localDataSource.cacheExpired();
    }

    @Override
    public void destroy() {
        localDataSource.destroy();
    }

    @Override
    public void getLastUseVersion(LoadDataCallBack<String> callBack) {
        localDataSource.getLastUseVersion(callBack);
    }

    @Override
    public void updateUseVersion() {
        localDataSource.updateUseVersion();
    }
}
