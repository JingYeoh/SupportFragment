package com.jkb.supportfragment.demo.business.launch.model;

import com.jkb.commonlib.base.frame.BaseModel;

/**
 * 启动页面：DataSource
 * Created by yj on 2017/5/5.
 */

public interface LaunchDataSource extends BaseModel {

    /**
     * 获取上次使用时候的版本号
     */
    void getLastUseVersion(LoadDataCallBack<String> callBack);

    /**
     * 更新使用的版本号
     */
    void updateUseVersion();
}
