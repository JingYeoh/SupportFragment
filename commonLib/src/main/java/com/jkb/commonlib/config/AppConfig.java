package com.jkb.commonlib.config;

/**
 * App配置类
 */

public interface AppConfig {

    /**
     * 路由路径
     */
    interface RouterPath {

        /*Fragment*/
        String ONBOARDING_CONTENT = "/onBoarding/content";
        String ONBOARDING_PLACEHOLDER = "/onBoarding/placeHolder";
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
}
