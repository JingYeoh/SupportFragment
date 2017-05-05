package com.jkb.supportfragment.demo.business.auth.account.model;

import com.jkb.commonlib.base.frame.BaseModel;
import com.jkb.supportfragment.demo.entity.auth.AreaCodeEntity;

import java.util.List;

/**
 * 帐号：数据来源
 * Created by yj on 2017/5/5.
 */

public interface AccountDataSource extends BaseModel {

    /**
     * 验证是否有该帐号
     */
    void identifyAccount(String account, LoadDataCallBack<Boolean> callBack);

    /**
     * 获取所有地区编号
     */
    void getAreaCodes(LoadDataCallBack<List<AreaCodeEntity>> callBack);
}
