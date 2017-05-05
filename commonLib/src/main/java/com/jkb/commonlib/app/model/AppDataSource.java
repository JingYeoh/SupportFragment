package com.jkb.commonlib.app.model;

import com.jkb.commonlib.base.frame.BaseModel;
import com.jkb.commonlib.db.entity.Status;
import com.jkb.commonlib.db.entity.User;

/**
 * App的数据来源接口
 * Created by yj on 2017/5/5.
 */

public interface AppDataSource extends BaseModel {
    /**
     * 得到用户
     */
    void getUser(int userId, LoadDataCallBack<User> callBack);

    /**
     * 添加或者更新用户信息
     */
    void insertOrUpdateUser(User users);

    /**
     * 保存状态表
     */
    void saveStatus(Status status);

    /**
     * 获取最后一条Status表数据
     */
    void getLastStatus(LoadDataCallBack<Status> callBack);
}
