package com.jkb.supportfragment.demo.business.auth.register.model.local;

import android.content.Context;

import com.jkb.commonlib.base.frame.i.IBaseLocalDataSource;
import com.jkb.commonlib.db.DbManager;
import com.jkb.commonlib.db.dao.DaoSession;
import com.jkb.commonlib.db.dao.UserDao;
import com.jkb.commonlib.db.entity.User;
import com.jkb.commonlib.utils.TimeUtils;
import com.jkb.supportfragment.demo.business.auth.register.model.RegisterDataSource;
import com.jkb.supportfragment.demo.entity.auth.RegisterEntity;
import com.jkb.supportfragment.demo.net.entity.PictureAttribute;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.UUID;

/**
 * 注册的本地数据来源类
 * Created by yj on 2017/5/12.
 */

public class RegisterLocalDataSource extends IBaseLocalDataSource implements RegisterDataSource {

    private RegisterLocalDataSource(Context context) {
        super(context);
    }

    public static RegisterLocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            synchronized (IBaseLocalDataSource.class) {
                if (sInstance == null) {
                    sInstance = new RegisterLocalDataSource(context);
                }
            }
        }
        return (RegisterLocalDataSource) sInstance;
    }

    @Override
    public void uploadPicture(String path, LoadDataCallBack<PictureAttribute> callBack) {

    }

    @Override
    public void register(RegisterEntity entity, LoadDataCallBack<User> callBack) {
        DaoSession daoSession = DbManager.getInstance().getDaoSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> builder = userDao.queryBuilder();
        QueryBuilder<User> qb = builder.where(UserDao.Properties.Phone.eq(entity.getAccount()));
        List<User> list = qb.list();
        if (list == null || list.isEmpty()) {
            User user = new User();
            List<User> list1 = builder.orderAsc(UserDao.Properties.UserId).list();
            long userId = 0;
            if (list1 != null) {
                userId = list1.size() + 1;
            }
            user.setId(userId);
            user.setAvatar(entity.getAvatarUrl());
            user.setPhone(entity.getAccount());
            user.setNickname(entity.getName());
            user.setPassword(entity.getPassword());
            user.setUpdateTime(TimeUtils.getCurrentTime());
            daoSession.insertOrReplace(user);
            callBack.onDataLoaded(user);
        } else {
            callBack.onDataNotAvailable("该手机号已被注册");
        }
    }
}
