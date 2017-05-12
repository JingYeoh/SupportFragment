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
import com.jkb.supportfragment.demo.business.helper.RepertoryInject;
import com.jkb.supportfragment.demo.databinding.FrgAuthVercodeBinding;
import com.jkb.supportfragment.demo.entity.auth.VerCodeEntity;
import com.jkb.supportfragment.demo.view.vercode.VerificationAction;
import com.jkb.supportfragment.demo.view.vercode.VerificationCodeEditText;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.lang.ref.WeakReference;

/**
 * 输入验证码
 * Created by yj on 2017/5/8.
 */
@Route(path = AppConfig.RouterPath.AUTH_VERCODE)
public class VerCodeFragment extends BaseFrameFragment<VerCodePresenter, FrgAuthVercodeBinding> implements
        VerCodeContract.View, View.OnClickListener {

    private static final int SMS_COUNT_SUM = 5;
    private static final int SMS_COUNT_EACH = 1000;
    private int mVerCodeType;
    private String mAccount;
    private CountHandler mHandler;

    @Override
    public void initPresenter() {
        new VerCodePresenter(this, RepertoryInject.provideVerCodeDR(mApplicationContext));
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
        EventBus.getDefault().register(this);
        Bundle args = savedInstanceState;
        if (savedInstanceState == null) {
            args = getArguments();
        }
        mVerCodeType = args.getInt(AppConfig.KeyBundle.VERCODE_TYPE);
        mAccount = args.getString(AppConfig.KeyBundle.ACCOUNT);
        getBinding().getVerCode().setPhoneNumber(mAccount);//设置手机号

        if (mHandler == null) {
            mHandler = new CountHandler(this);
        }
        getPresenter().start();
    }

    @Override
    public void initListener() {
        findViewById(R.id.verCode_continue).setOnClickListener(this);
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.vercode_sendSMS).setOnClickListener(this);

        ((VerificationCodeEditText) findViewById(R.id.fav_codeEditText)).
                setOnVerificationCodeChangedListener(onCodeChangedListener);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(AppConfig.KeyBundle.ACCOUNT, mAccount);
        outState.putInt(AppConfig.KeyBundle.VERCODE_TYPE, mVerCodeType);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mHandler = null;
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
                getPresenter().identifyVerCode();
                break;
        }
    }

    /**
     * 接收到登录成功的消息通知
     */
    @Subscriber(tag = AppConfig.EventBusTAG.LOGIN_SUCCESS)
    public void receiveMessageLoginSuccess(Message message) {
        close();
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
        mHandler.startCount();
    }

    @Override
    public void launchRegister() {
        startFragment(AppLauncher.launchRegister(mAccount));
    }

    /**
     * 用于倒计时的Handler
     */
    private static class CountHandler extends Handler {

        private WeakReference<VerCodeFragment> verCodeFragment;

        private CountHandler(VerCodeFragment fragment) {
            verCodeFragment = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            VerCodeFragment fragment = this.verCodeFragment.get();
            fragment.getBinding().getVerCode().setSendCount(msg.arg1);
            if (msg.arg1 == 0) return;
            msg.arg1--;
            Message message = Message.obtain();
            message.arg1 = msg.arg1;
            sendMessageDelayed(message, SMS_COUNT_EACH);
        }

        /**
         * 开始倒计时
         */
        void startCount() {
            Message message = Message.obtain();
            if (message.arg1 > 0) return;
            message.arg1 = SMS_COUNT_SUM;
            sendMessageDelayed(message, SMS_COUNT_EACH);
        }
    }

    /**
     * 验证码变化时候的监听事件
     */
    private VerificationAction.OnVerificationCodeChangedListener onCodeChangedListener =
            new VerificationAction.OnVerificationCodeChangedListener() {
                @Override
                public void onVerCodeChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void onInputCompleted(CharSequence s) {
                    if (getPresenter() != null) getPresenter().identifyVerCode();
                }
            };
}
