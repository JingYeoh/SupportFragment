package com.jkb.supportfragment.demo.business.auth.verCode.contract;

import com.jkb.commonlib.base.frame.BasePresenter;
import com.jkb.commonlib.base.frame.BaseView;
import com.jkb.supportfragment.demo.entity.auth.VerCodeEntity;

/**
 * 验证码:页面协议类
 * Created by yj on 2017/5/8.
 */

public interface VerCodeContract {

    interface View extends BaseView<Presenter> {
        /**
         * 绑定验证码的数据实体类
         */
        void bindVerCodeEntity(VerCodeEntity entity);

        /**
         * 开始倒计时
         */
        void startSmsCount();

        /**
         * 启动登录页面
         */
        void launchLogin();
    }

    interface Presenter extends BasePresenter {
        /**
         * 发送验证码
         */
        void sendVerCode();
    }
}
