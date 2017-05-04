package com.jkb.supportfragment.demo.business.onboarding;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.helper.AppLauncher;

/**
 * 新手上路的页面适配器
 * Created by yj on 2017/5/4.
 */

public class OnBoardingPagerAdapter extends FragmentPagerAdapter {

    public OnBoardingPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return AppLauncher.launchOnBoardingPlaceHolder(AppConfig.OnBoardType.ONE);
        } else if (position == 1) {
            return AppLauncher.launchOnBoardingPlaceHolder(AppConfig.OnBoardType.TWO);
        } else if (position == 2) {
            return AppLauncher.launchOnBoardingPlaceHolder(AppConfig.OnBoardType.THREE);
        } else if (position == 3) {
            return AppLauncher.launchOnBoardingPlaceHolder(AppConfig.OnBoardType.FOUR);
        } else {
            return AppLauncher.launchOnBoardingPlaceHolder(AppConfig.OnBoardType.FIVE);
        }
    }
}
