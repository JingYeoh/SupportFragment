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
    }

    /**
     * 时间总线的TAG
     */
    interface EventBusTAG {
        String APP_INIT = "/app/init";
        String APP_INIT_COMPLECTED = "/app/init/completed";
    }

    /**
     * 用于保存和恢复的Key
     */
    interface KeyBundle {
        String ONBOARDING_TYPE = "keyBundle.onBoardingType";
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
     * 数据库
     */
    interface Db {
        String NAME = "tantan.db";
    }
}
