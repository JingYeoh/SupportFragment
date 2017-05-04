package com.jkb.commonlib.db;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.db.dao.DaoMaster;
import com.jkb.commonlib.db.dao.DaoSession;

/**
 * 数据库管理类
 * 使用设计模式，单例模式
 * Created by yj on 2017/5/4.
 */

public final class DbManager {

    private DbManager() {
    }

    private volatile static DbManager sInstance = null;
    private Context mContext;

    //db
    private DaoMaster.DevOpenHelper mDevOpenHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    /**
     * 返回DbManager的实例
     */
    public static DbManager getInstance() {
        if (sInstance == null) {
            synchronized (DbManager.class) {
                if (sInstance == null) {
                    sInstance = new DbManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * Init,It mast be called before use db
     */
    public void init(Application application) {
        mContext = application.getApplicationContext();
        mDevOpenHelper = new DaoMaster.DevOpenHelper(mContext, AppConfig.Db.NAME);//创建数据库
    }

    /**
     * 获取可写数据库
     **/
    private SQLiteDatabase getWritableDatabase() {
        return mDevOpenHelper.getWritableDatabase();
    }

    /**
     * 获取DaoMaster
     **/
    private DaoMaster getDaoMaster() {
        if (null == mDaoMaster) {
            synchronized (DbManager.class) {
                if (null == mDaoMaster) {
                    mDaoMaster = new DaoMaster(getWritableDatabase());
                }
            }
        }
        return mDaoMaster;
    }

    /**
     * 返回DaoSession
     */
    public DaoSession getDaoSession() {
        if (null == mDaoSession) {
            synchronized (DbManager.class) {
                mDaoSession = getDaoMaster().newSession();
            }
        }
        return mDaoSession;
    }
}
