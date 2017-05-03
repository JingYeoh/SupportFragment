package com.jkb.supportfragment.demo;

import android.os.Bundle;

import com.jkb.commonlib.base.BaseActivity;

/**
 * 单Activity+多Fragment架构的主Activity
 */
public class MainActivity extends BaseActivity {

    @Override
    public int getRootViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public int getFragmentContentId() {
        return R.id.mainFrameContent;
    }
}
