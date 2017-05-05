package com.jkb.commonlib.app;

import android.content.Context;

import com.jkb.commonlib.db.entity.User;
import com.jkb.commonlib.exception.auth.AuthException;

/**
 * App的动作
 * Created by yj on 2017/5/5.
 */

public interface AppAction {
    /**
     * 更新用户信息
     */
    void updateUserInfo(User user) throws AuthException;

    /**
     * 得到当前用户
     */
    User getCurrentUser() throws AuthException;

    /**
     * 是否登录
     */
    boolean isLogin();

    /**
     * 系统错误
     */
    void onSystemError();

    /**
     * 登出系統
     */
    void logoutSystem();

    /**
     * 登录系统
     * 传递参数：用户id
     * 从本地数据库中找用户id
     */
    void loginSystem(int userId);

    /**
     * 得到系统的版本号
     *
     * @return 系统当前的版本号
     */
    String getSystemVersion();

    /**
     * 获取ApplicationContext
     */
    Context getApplicationContext();
}
