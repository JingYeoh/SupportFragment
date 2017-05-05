package com.jkb.commonlib.app.model;

import com.jkb.commonlib.db.DbManager;
import com.jkb.commonlib.db.dao.DaoSession;
import com.jkb.commonlib.db.dao.StatusDao;
import com.jkb.commonlib.db.dao.UserDao;
import com.jkb.commonlib.db.entity.Status;
import com.jkb.commonlib.db.entity.User;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * App的仓库类
 * Created by yj on 2017/5/5.
 */

public class AppDataRepertory implements AppDataSource {

    private DaoSession daoSession;

    private AppDataRepertory() {
        daoSession = DbManager.getInstance().getDaoSession();
    }

    private static AppDataRepertory sInstance;

    public static AppDataRepertory getInstance() {
        if (sInstance == null) {
            synchronized (AppDataRepertory.class) {
                if (sInstance == null) {
                    sInstance = new AppDataRepertory();
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
        daoSession = null;
    }

    @Override
    public void getUser(int userId, LoadDataCallBack<User> callBack) {
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> builder = userDao.queryBuilder();
        builder.where(UserDao.Properties.UserId.eq(userId));
        List<User> list = builder.list();
        if (list == null || list.size() == 0) {
            callBack.onDataNotAvailable("user table is empty");
        } else {
            callBack.onDataLoaded(list.get(0));
        }
    }

    @Override
    public void insertOrUpdateUser(User users) {
        daoSession.insertOrReplace(users);
    }

    @Override
    public void saveStatus(Status status) {
        daoSession.insertOrReplace(status);
    }

    @Override
    public void getLastStatus(LoadDataCallBack<Status> callBack) {
        StatusDao statusDao = daoSession.getStatusDao();
        QueryBuilder<Status> qb = statusDao.queryBuilder();
        QueryBuilder<Status> builder = qb.orderDesc(StatusDao.Properties.Create_time);
        List<Status> list = builder.list();
        if (list == null || list.size() == 0) {
            callBack.onDataNotAvailable("status table is empty");
        } else {
            callBack.onDataLoaded(list.get(0));
        }
    }
}
