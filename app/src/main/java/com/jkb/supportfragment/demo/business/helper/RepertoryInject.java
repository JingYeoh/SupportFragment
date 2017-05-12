package com.jkb.supportfragment.demo.business.helper;

import android.content.Context;

import com.jkb.commonlib.base.frame.BaseModel;
import com.jkb.supportfragment.demo.business.auth.account.model.AccountDataRepertory;
import com.jkb.supportfragment.demo.business.auth.account.model.local.AccountLocalDataSource;
import com.jkb.supportfragment.demo.business.auth.account.model.remote.AccountRemoteDataSource;
import com.jkb.supportfragment.demo.business.auth.register.model.RegisterDataRepertory;
import com.jkb.supportfragment.demo.business.auth.register.model.local.RegisterLocalDataSource;
import com.jkb.supportfragment.demo.business.auth.register.model.remote.RegisterRemoteDataSource;
import com.jkb.supportfragment.demo.business.auth.verCode.model.VerCodeDataRepertory;
import com.jkb.supportfragment.demo.business.auth.verCode.model.local.VerCodeLocalDataSource;
import com.jkb.supportfragment.demo.business.auth.verCode.model.remote.VerCodeRemoteDataSource;
import com.jkb.supportfragment.demo.business.launch.model.LaunchDataRepertory;
import com.jkb.supportfragment.demo.business.launch.model.local.LaunchLocalDataSource;

/**
 * 数据仓库类的Inject
 * Created by yj on 2017/5/5.
 */

public class RepertoryInject {

    /**
     * 返回启动页数据仓库
     */
    public static LaunchDataRepertory provideLaunchDR(Context context) {
        return LaunchDataRepertory.getInstance(LaunchLocalDataSource.getInstance(context));
    }

    /**
     * 返回帐号页数据仓库
     */
    public static AccountDataRepertory provideAccountDR(Context context) {
        return AccountDataRepertory.getInstance(AccountLocalDataSource.getInstance(context),
                AccountRemoteDataSource.getInstance());
    }

    /**
     * 返回验证码页数据仓库
     */
    public static VerCodeDataRepertory provideVerCodeDR(Context context) {
        return VerCodeDataRepertory.getInstance(VerCodeLocalDataSource.getInstance(context),
                VerCodeRemoteDataSource.getInstance());
    }

    /**
     * 返回注册数据仓库
     */
    public static RegisterDataRepertory provideRegisterDR(Context context) {
        return RegisterDataRepertory.getInstance(RegisterLocalDataSource.getInstance(context),
                RegisterRemoteDataSource.getInstance());
    }
}