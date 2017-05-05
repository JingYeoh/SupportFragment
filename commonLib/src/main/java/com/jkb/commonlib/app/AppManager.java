package com.jkb.commonlib.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.jkb.commonlib.app.model.AppDataRepertory;
import com.jkb.commonlib.base.frame.BaseModel;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.db.entity.Status;
import com.jkb.commonlib.db.entity.User;
import com.jkb.commonlib.exception.auth.AuthException;
import com.jkb.commonlib.exception.auth.UnLoginException;
import com.jkb.commonlib.exception.auth.UserInfoNotFoundException;
import com.jkb.commonlib.utils.TimeUtils;

import org.simple.eventbus.EventBus;

import java.util.Observable;

/**
 * App的管理类
 * 处理App身份权限控制
 * 采用观察者模式
 * Created by yj on 2017/5/5.
 */

public class AppManager extends Observable implements AppAction {

    private static AppManager sInstance = null;

    private Context mContext;
    private AppDataRepertory mRepertory;
    private User mUser;

    private AppManager() {
    }

    /**
     * 返回AppManager实例
     */
    public static AppManager getInstance() {
        if (sInstance == null) {
            synchronized (AppManager.class) {
                if (sInstance == null) {
                    sInstance = new AppManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * 初始化
     */
    public void init(Application application) {
        mContext = application.getApplicationContext();
        mRepertory = AppDataRepertory.getInstance();
        mRepertory.getLastStatus(new BaseModel.LoadDataCallBack<Status>() {
            @Override
            public void onDataLoaded(Status data) {
                if (data.isFlag_login()) {
                    loginSystem(data.getUser_id());
                } else {
                    logoutSystem();
                }
            }

            @Override
            public void onDataNotAvailable(String errData) {
                logoutSystem();
            }
        });
    }

    /**
     * 通知系统状态改变
     *
     * @param systemStatus 系统状态（常量）
     */
    private void notifyStatusChanged(int systemStatus) {
        setChanged();
        notifyObservers(systemStatus);
    }

    @Override
    public void updateUserInfo(User user) throws AuthException {
        if (!isLogin()) {
            throw new UnLoginException("system is logout status");
        }
        if (user == null) {
            throw new UserInfoNotFoundException("system is logout status");
        }
        mUser.setAvatar(user.getAvatar());
        mUser.setNickname(user.getNickname());
        mUser.setPassword(user.getPassword());
        mUser.setUpdateTime(TimeUtils.getCurrentTime());
        //存储用户信息

        insertStatus(true);//更新状态表
    }

    @Override
    public User getCurrentUser() throws AuthException {
        if (!isLogin()) {
            throw new UnLoginException("system is logout status");
        }
        if (mUser == null) {
            throw new UserInfoNotFoundException("user is not found");
        }
        return mUser;
    }

    @Override
    public boolean isLogin() {
        return mUser != null;
    }

    @Override
    public void onSystemError() {
        setAppStatusToLogout();
        notifyStatusChanged(AppConfig.AppStatus.ERROR);
    }

    @Override
    public void logoutSystem() {
        setAppStatusToLogout();
        notifyStatusChanged(AppConfig.AppStatus.LOGOUT);
    }

    @Override
    public void loginSystem(int userId) {
        mRepertory.getUser(userId, new BaseModel.LoadDataCallBack<User>() {
            @Override
            public void onDataLoaded(User data) {
                mUser = data;
                notifyStatusChanged(AppConfig.AppStatus.LOGIN);
                insertStatus(true);
            }

            @Override
            public void onDataNotAvailable(String errData) {
                onSystemError();//用户信息得到错误
            }
        });
    }

    @Override
    public String getSystemVersion() {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public Context getApplicationContext() {
        return mContext;
    }

    /**
     * 设置App的状态为未登录
     */
    private void setAppStatusToLogout() {
        mUser = null;
        insertStatus(false);//插入状态表
    }

    /**
     * 插入状态表
     */
    private void insertStatus(boolean isLogin) {
        Status status = new Status();
        status.setFlag_login(isLogin);
        if (isLogin) {
            status.setUser_id(mUser.getUserId());
        } else {
            status.setUser_id(-1);
        }
        status.setCreate_time(TimeUtils.getCurrentTime());
    }
}
