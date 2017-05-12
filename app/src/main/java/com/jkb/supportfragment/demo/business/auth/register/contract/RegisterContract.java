package com.jkb.supportfragment.demo.business.auth.register.contract;

import android.net.Uri;

import com.jkb.commonlib.base.frame.BasePresenter;
import com.jkb.commonlib.base.frame.BaseView;
import com.jkb.supportfragment.demo.entity.auth.PasswordEntity;
import com.jkb.supportfragment.demo.entity.auth.RegisterEntity;

/**
 * 设置密码：Contract
 * Created by yj on 2017/5/12.
 */

public interface RegisterContract {

    interface View extends BaseView<Presenter> {
        /**
         * 绑定页面数据
         */
        void bindPasswordEntity(RegisterEntity entity);

        /**
         * 注册成功
         */
        void registerSuccess();

        /**
         * 启动App主页
         */
        void launchAppMain();

        /**
         * 启动用户协议页面
         */
        void launchUserRules();
    }

    interface Presenter extends BasePresenter {
        /**
         * 注册
         */
        void register();

        /**
         * 上传图片
         */
        void uploadPicture(String path);
    }
}
