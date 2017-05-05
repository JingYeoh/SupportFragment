package com.jkb.supportfragment.demo.business.launch.contract;

import com.jkb.commonlib.base.frame.BasePresenter;
import com.jkb.commonlib.base.frame.BaseView;
import com.jkb.supportfragment.demo.entity.launch.LaunchEntity;

/**
 * 启动页面协议类
 * Created by yj on 2017/5/5.
 */

public interface LaunchContract {

    interface View extends BaseView<Presenter> {

        /**
         * 设置启动页数据实体
         */
        void setLaunchEntity(LaunchEntity entity);

        /**
         * 启动新手上路
         */
        void launchOnBoarding();

        /**
         * 启动主页
         */
        void launchMain();

        /**
         * 启动输入帐号
         */
        void launchAccount();
    }

    interface Presenter extends BasePresenter {

    }
}
