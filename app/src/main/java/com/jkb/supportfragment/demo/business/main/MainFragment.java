package com.jkb.supportfragment.demo.business.main;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.base.ui.BaseFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.supportfragment.demo.R;

/**
 * App主页
 * Created by yj on 2017/5/12.
 */
@Route(path = AppConfig.RouterPath.APP_MAIN)
public class MainFragment extends BaseFragment {

    @Override
    public int getRootViewId() {
        return R.layout.frg_main;
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
}
