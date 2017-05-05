package com.jkb.supportfragment.demo.business.helper;

import android.content.Context;

import com.jkb.supportfragment.demo.business.launch.model.LaunchDataRepertory;
import com.jkb.supportfragment.demo.business.launch.model.local.LaunchLocalDataSource;

/**
 * 数据仓库类的Inject
 * Created by yj on 2017/5/5.
 */

public class InjectRepertory {

    /**
     * 返回启动页数据仓库
     */
    public static LaunchDataRepertory provideLaunchDR(Context context) {
        return LaunchDataRepertory.getInstance(LaunchLocalDataSource.getInstance(context));
    }
}