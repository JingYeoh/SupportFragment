package com.jkb.supportfragment.demo.business.auth.account;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.base.ui.BaseFrameFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.support.utils.LogUtils;
import com.jkb.supportfragment.demo.R;
import com.jkb.supportfragment.demo.business.auth.account.contract.AccountContract;
import com.jkb.supportfragment.demo.business.auth.account.presenter.AccountPresenter;
import com.jkb.supportfragment.demo.business.helper.InjectRepertory;
import com.jkb.supportfragment.demo.databinding.FrgAuthAccountBinding;
import com.jkb.supportfragment.demo.entity.auth.AccountEntity;
import com.jkb.supportfragment.demo.entity.auth.AreaCodeEntity;

import java.util.List;

/**
 * 帐号
 * Created by yj on 2017/5/5.
 */
@Route(path = AppConfig.RouterPath.AUTH_ACCOUNT)
public class AccountFragment extends BaseFrameFragment<AccountPresenter, FrgAuthAccountBinding> implements
        AccountContract.View, View.OnClickListener {

    @Override
    public int getRootViewId() {
        return R.layout.frg_auth_account;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        getPresenter().start();
    }

    @Override
    public void initListener() {
        findViewById(R.id.account_continue).setOnClickListener(this);
    }

    @Override
    public void initPresenter() {
        new AccountPresenter(this, InjectRepertory.provideAccountDR(mContext));
    }

    @Override
    public void setPresenter(AccountContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setAccountEntity(AccountEntity entity) {
        getBinding().setAccount(entity);
    }

    @Override
    public void showAreaCode(List<AreaCodeEntity> areaCode) {

    }

    @Override
    public void launchLogin(String account) {

    }

    @Override
    public void launchVerCode(String account) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_continue:
                showShortToast("continue");
                getPresenter().onContinueClick();
                break;
        }
    }
}
