package com.jkb.supportfragment.demo.business.onboarding;

import android.os.Bundle;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.base.BaseFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.supportfragment.demo.R;

/**
 * 新手上路的占位
 * Created by yj on 2017/5/4.
 */
@Route(path = AppConfig.RouterPath.ONBOARDING_PLACEHOLDER)
public class OnBoardingPlaceHolderFragment extends BaseFragment {

    public static OnBoardingPlaceHolderFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(AppConfig.KeyBundle.ONBOARDING_TYPE, type);
        OnBoardingPlaceHolderFragment fragment = new OnBoardingPlaceHolderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //新手上路的类型
    private int mOnBoardingType;
    //ui
    private ImageView imageView;

    @Override
    public int getRootViewId() {
        return R.layout.frg_onboarding_placeholder;
    }

    @Override
    public void initView() {
        imageView = (ImageView) findViewById(R.id.onBoarding_placeHolder_imageView);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle args = savedInstanceState;
        if (savedInstanceState == null) {
            args = getArguments();
        }
        mOnBoardingType = args.getInt(AppConfig.KeyBundle.ONBOARDING_TYPE);
        initImage();
    }

    /**
     * 初始化图片
     */
    private void initImage() {
        switch (mOnBoardingType) {
            case AppConfig.OnBoardType.ONE:
                imageView.setImageResource(R.drawable.ic_guide_01);
                break;
            case AppConfig.OnBoardType.TWO:
                imageView.setImageResource(R.drawable.ic_guide_02);
                break;
            case AppConfig.OnBoardType.THREE:
                imageView.setImageResource(R.drawable.ic_guide_03);
                break;
            case AppConfig.OnBoardType.FOUR:
                imageView.setImageResource(R.drawable.ic_guide_04);
                break;
            case AppConfig.OnBoardType.FIVE:
                imageView.setImageResource(R.drawable.ic_guide_05);
                break;
            default:
                imageView.setImageResource(R.drawable.ic_guide_05);
                break;
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(AppConfig.KeyBundle.ONBOARDING_TYPE, mOnBoardingType);
    }
}
