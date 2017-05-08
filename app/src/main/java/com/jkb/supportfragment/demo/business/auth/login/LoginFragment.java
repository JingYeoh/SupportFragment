package com.jkb.supportfragment.demo.business.auth.login;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.base.ui.BaseFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.supportfragment.demo.R;

/**
 * 登录
 * Created by yj on 2017/5/8.
 */
@Route(path = AppConfig.RouterPath.AUTH_LOGIN)
public class LoginFragment extends BaseFragment {

    private String mAccount;

    @Override
    public int getRootViewId() {
        return R.layout.frg_auth_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle args = savedInstanceState;
        if (savedInstanceState == null) {
            args = getArguments();
        }
        mAccount = args.getString(AppConfig.KeyBundle.ACCOUNT);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(AppConfig.KeyBundle.ACCOUNT, mAccount);
    }
}
