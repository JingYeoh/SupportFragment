package com.jkb.supportfragment.demo.business.auth.verCode;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.base.ui.BaseFrameFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.helper.AppLauncher;
import com.jkb.supportfragment.demo.R;
import com.jkb.supportfragment.demo.business.auth.verCode.contract.VerCodeContract;
import com.jkb.supportfragment.demo.business.auth.verCode.presenter.VerCodePresenter;
import com.jkb.supportfragment.demo.business.helper.InjectRepertory;
import com.jkb.supportfragment.demo.databinding.FrgAuthVercodeBinding;
import com.jkb.supportfragment.demo.entity.auth.VerCodeEntity;

/**
 * 输入验证码
 * Created by yj on 2017/5/8.
 */
@Route(path = AppConfig.RouterPath.AUTH_VERCODE)
public class VerCodeFragment extends BaseFrameFragment<VerCodePresenter, FrgAuthVercodeBinding> implements
        VerCodeContract.View, View.OnClickListener {

    private static final int WHAT_SMS_COUNT = 1001;
    private static final int SMS_COUNT_SUM = 60;
    private static final int SMS_COUNT_EACH = 1000;
    private int mVerCodeType;
    private String mAccount;

    @Override
    public void initPresenter() {
        new VerCodePresenter(this, InjectRepertory.provideVerCodeDR(mApplicationContext));
    }

    @Override
    public int getRootViewId() {
        return R.layout.frg_auth_vercode;
    }

    @Override
    public void initView() {
        ((TextView) findViewById(R.id.title_name)).setText(getString(R.string.send_verCode));
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle args = savedInstanceState;
        if (savedInstanceState == null) {
            args = getArguments();
        }
        mVerCodeType = args.getInt(AppConfig.KeyBundle.VERCODE_TYPE);
        mAccount = args.getString(AppConfig.KeyBundle.ACCOUNT);
        getBinding().getVerCode().setPhoneNumber(mAccount);//设置手机号

        getPresenter().start();
    }

    @Override
    public void initListener() {
        findViewById(R.id.verCode_continue).setOnClickListener(this);
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.vercode_sendSMS).setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(AppConfig.KeyBundle.ACCOUNT, mAccount);
        outState.putInt(AppConfig.KeyBundle.VERCODE_TYPE, mVerCodeType);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vercode_sendSMS://获取验证码
                getPresenter().sendVerCode();
                break;
            case R.id.title_back:
                onBackPressed();
                break;
            case R.id.verCode_continue://继续
                break;
        }
    }

    @Override
    public void setPresenter(VerCodeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void bindVerCodeEntity(VerCodeEntity entity) {
        getBinding().setVerCode(entity);
    }

    @Override
    public void startSmsCount() {
        Message message = Message.obtain();
        message.what = WHAT_SMS_COUNT;
        message.arg1 = SMS_COUNT_SUM;
        handler.sendMessageDelayed(message, SMS_COUNT_EACH);
    }

    @Override
    public void launchLogin() {
        startFragment(AppLauncher.launchLogin(mAccount));
    }

    /**
     * 用于倒计时的Handler
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.arg1 == 0) return;
            getBinding().getVerCode().setSendCount(msg.arg1);
            msg.arg1--;
            handler.sendMessageDelayed(msg, SMS_COUNT_EACH);
        }
    };
}
