package com.jkb.supportfragment.demo.business.auth.account.contract;

import com.jkb.commonlib.base.frame.BasePresenter;
import com.jkb.commonlib.base.frame.BaseView;
import com.jkb.supportfragment.demo.entity.auth.AccountEntity;
import com.jkb.supportfragment.demo.entity.auth.AreaCodeEntity;

import java.util.List;

/**
 * 帐号的页面协议类
 * Created by yj on 2017/5/5.
 */

public interface AccountContract {

    interface View extends BaseView<Presenter> {

        /**
         * 设置帐号实体类
         */
        void setAccountEntity(AccountEntity entity);

        /**
         * 显示地区编号
         */
        void showAreaCode(List<AreaCodeEntity> areaCode);

        /**
         * 启动登录页面
         */
        void launchLogin(String account);

        /**
         * 启动验证码页面
         */
        void launchVerCode(String account);
    }

    interface Presenter extends BasePresenter {
        /**
         * 点击继续
         */
        void onContinueClick();
    }
}
