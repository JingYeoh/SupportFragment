package com.jkb.commonlib.config;

/**
 * App配置类
 */

public interface AppConfig {

    /**
     * 路由路径
     * 注意：path最少两级
     */
    interface RouterPath {
        /*Fragment*/
        String ONBOARDING_CONTENT = "/onBoarding/content";
        String ONBOARDING_PLACEHOLDER = "/onBoarding/placeholder";

        String AUTH_ACCOUNT = "/auth/account";
        String AUTH_LOGIN = "/auth/login";
        String AUTH_PASSWORD_FINDBACK = "/auth/password/findBack";
        String AUTH_PASSWORD_RESET = "/auth/password/reset";
        String AUTH_AREA_CODE = "/auth/areaCode";
        String AUTH_VERCODE = "/auth/verCode";

        String APP_MAIN = "/app/main";
    }

    /**
     * 时间总线的TAG
     */
    interface EventBusTAG {
        String APP_INIT = "/app/init";
        String APP_INIT_COMPLECTED = "/app/init/completed";
        String APP_ONBOARDING_COMPLECTED = "/app/onBoarding/completed";

        String AUTH_AREA_CODE_SELECTE = "/auth/areaCode/select";
    }

    /**
     * 用于保存和恢复的Key
     */
    interface KeyBundle {
        String ONBOARDING_TYPE = "onBoardingType";
        String AREACODE = "areaCode";
        String ACCOUNT = "account";
        String VERCODE_TYPE = "verCodeType";
    }

    /**
     * 新手上路类型
     */
    interface OnBoardType {
        int ONE = 1001;
        int TWO = 1002;
        int THREE = 1003;
        int FOUR = 1004;
        int FIVE = 1005;
    }

    /**
     * 验证码场景
     */
    interface VerCodeType {
        int REGISTER = 1001;//注册
        int PASSWORD_RESET = 1002;//重置密码
        int PASSWORD_FINDBACK = 1003;//找回密码
    }

    /**
     * 数据库
     */
    interface Db {
        String NAME = "tantan.db";
    }

    /**
     * SharedPreference
     */
    interface SharedPreference {
        String NAME_APP = "tantanApp";

        String KEY_LASH_USE_VERSION = "lastUseVersion";
    }

    /**
     * App状态
     */
    interface AppStatus {
        int LOGIN = 1001;//登录
        int UNLOGIN = 1002;//未登录
        int LOGOUT = 1003;//登出
        int ERROR = 1004;//错误
    }
}
