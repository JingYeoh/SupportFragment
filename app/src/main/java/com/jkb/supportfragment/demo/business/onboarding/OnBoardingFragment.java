package com.jkb.supportfragment.demo.business.onboarding;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.base.ui.BaseFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.helper.AppLauncher;
import com.jkb.commonlib.ui.annotation.SupportContent;
import com.jkb.commonlib.ui.annotation.SupportWindow;
import com.jkb.commonlib.utils.DimensionUtils;
import com.jkb.supportfragment.demo.R;

import org.simple.eventbus.EventBus;

/**
 * 新手上路
 * Created by yj on 2017/5/4.
 */
@SupportWindow(fullScreen = true)
@SupportContent(contentViewId = R.layout.frg_onboarding)
@Route(path = AppConfig.RouterPath.ONBOARDING_CONTENT)
public class OnBoardingFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    //ui
    private ViewPager viewPager;
    private LinearLayout llCoordinate;
    private View btFinish;
    //data
    private OnBoardingPagerAdapter onBoardingPagerAdapter;
    private int[] onBoardingColors;

    @Override
    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.onBoarding_viewPager);
        llCoordinate = (LinearLayout) findViewById(R.id.onBoarding_coordinate);
        btFinish = findViewById(R.id.onBoarding_finish);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        onBoardingPagerAdapter = new OnBoardingPagerAdapter(mChildFm);
        viewPager.setAdapter(onBoardingPagerAdapter);
        //初始化引导指标
        initCoordinate();
        initColors();//初始化颜色

        selectCoordinate(0);
    }

    @Override
    public void initListener() {
        btFinish.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.onBoarding_finish://引导结束
                launchApp();
                break;
        }
    }

    /**
     * 启动App
     */
    private void launchApp() {
        startFragment(AppLauncher.launchAccount());
//        close();
    }

    /**
     * 初始化引导指标
     */
    private void initCoordinate() {
        for (int i = 0; i < onBoardingPagerAdapter.getCount(); i++) {
            ImageView iv = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            int dp_5 = DimensionUtils.dp2px(mContext, 5);
            params.setMargins(dp_5, 0, dp_5, 0);
            iv.setLayoutParams(params);
            iv.setBackgroundResource(R.drawable.selector_dot);
            llCoordinate.addView(iv);
        }
    }

    /**
     * 初始化颜色
     */
    private void initColors() {
        int[] colorRes = new int[]{
                R.color.guide_1, R.color.guide_2, R.color.guide_3, R.color.guide_4, R.color.guide_5
        };
        onBoardingColors = new int[colorRes.length];
        for (int i = 0; i < onBoardingPagerAdapter.getCount(); i++) {
            onBoardingColors[i] = ContextCompat.getColor(mContext, colorRes[i]);
        }
    }

    /**
     * 选择引导指标
     */
    private void selectCoordinate(int position) {
        for (int i = 0; i < llCoordinate.getChildCount(); i++) {
            llCoordinate.getChildAt(i).setSelected(false);
        }
        llCoordinate.getChildAt(position).setSelected(true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int nextPosition = position == onBoardingPagerAdapter.getCount() - 1 ? position : position + 1;
        int colorUpdate = (Integer) new ArgbEvaluator().evaluate(positionOffset,
                onBoardingColors[position], onBoardingColors[nextPosition]);
        viewPager.setBackgroundColor(colorUpdate);
    }

    @Override
    public void onPageSelected(int position) {
        selectCoordinate(position);//切换点
        if (position == onBoardingPagerAdapter.getCount() - 1) {
            btFinish.setVisibility(View.VISIBLE);
        } else {
            btFinish.setVisibility(View.GONE);
        }
        rootView.setBackgroundColor(onBoardingColors[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
