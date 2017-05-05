package com.jkb.supportfragment.demo.business.auth.account;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.base.ui.BaseFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.supportfragment.demo.R;

/**
 * 帐号
 * Created by yj on 2017/5/5.
 */
@Route(path = AppConfig.RouterPath.AUTH_ACCOUNT)
public class AccountFragment extends BaseFragment {

    @Override
    public int getRootViewId() {
        return R.layout.frg_auth_account;
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
