package com.jkb.supportfragment.demo.business.auth.register;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.base.ui.BaseFrameFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.helper.AppLauncher;
import com.jkb.supportfragment.demo.R;
import com.jkb.supportfragment.demo.business.auth.register.contract.RegisterContract;
import com.jkb.supportfragment.demo.business.auth.register.presenter.RegisterPresenter;
import com.jkb.supportfragment.demo.business.helper.RepertoryInject;
import com.jkb.supportfragment.demo.databinding.FrgAuthRegisterBinding;
import com.jkb.supportfragment.demo.entity.auth.RegisterEntity;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

/**
 * 注册
 * Created by yj on 2017/5/12.
 */
@Route(path = AppConfig.RouterPath.AUTH_REGISTER)
public class RegisterFragment extends BaseFrameFragment<RegisterPresenter, FrgAuthRegisterBinding> implements
        RegisterContract.View, View.OnClickListener {

    private String mAccount;

    @Override
    public void initPresenter() {
        new RegisterPresenter(this, RepertoryInject.provideRegisterDR(mApplicationContext));
    }

    @Override
    public int getRootViewId() {
        return R.layout.frg_auth_register;
    }

    @Override
    public void initView() {
        ((TextView) findViewById(R.id.title_name)).setText(R.string.register_new_user);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        Bundle args = savedInstanceState;
        if (savedInstanceState == null) {
            args = getArguments();
        }
        mAccount = args.getString(AppConfig.KeyBundle.ACCOUNT);

        getPresenter().start();
    }

    @Override
    public void initListener() {
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.register_continue).setOnClickListener(this);
        findViewById(R.id.register_birthday).setOnClickListener(this);
        findViewById(R.id.register_avatar).setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(AppConfig.KeyBundle.ACCOUNT, mAccount);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                onBackPressed();
                break;
            case R.id.register_continue:
                getPresenter().register();
                break;
            case R.id.register_birthday:
                break;
            case R.id.register_avatar:
                break;
        }
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void bindPasswordEntity(RegisterEntity entity) {
        getBinding().setRegister(entity);
        getBinding().getRegister().setAccount(mAccount);
    }

    @Override
    public void registerSuccess() {
        EventBus.getDefault().post(Message.obtain(), AppConfig.EventBusTAG.LOGIN_SUCCESS);
    }

    @Override
    public void launchAppMain() {
        startFragment(AppLauncher.launchAppMain());
    }

    @Override
    public void launchUserRules() {

    }

    /**
     * 接收到登录成功的消息通知
     */
    @Subscriber(tag = AppConfig.EventBusTAG.LOGIN_SUCCESS)
    public void receiveMessageLoginSuccess(Message message) {
        close();
    }
}
