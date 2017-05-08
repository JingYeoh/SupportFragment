package com.jkb.supportfragment.demo.business.auth.account.presenter;

import com.jkb.commonlib.base.frame.BaseModel;
import com.jkb.commonlib.utils.PatternUtils;
import com.jkb.support.utils.LogUtils;
import com.jkb.supportfragment.demo.business.auth.account.contract.AccountContract;
import com.jkb.supportfragment.demo.business.auth.account.model.AccountDataRepertory;
import com.jkb.supportfragment.demo.entity.auth.AccountEntity;
import com.jkb.supportfragment.demo.entity.auth.AreaCodeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 帐号：Presenter
 * Created by yj on 2017/5/5.
 */

public class AccountPresenter implements AccountContract.Presenter, BaseModel.LoadDataCallBack<List<AreaCodeEntity>> {

    private AccountContract.View mView;
    private AccountDataRepertory mRepertory;

    private AccountEntity accountEntity;

    public AccountPresenter(AccountContract.View mView, AccountDataRepertory mRepertory) {
        this.mView = mView;
        this.mRepertory = mRepertory;
        this.mView.setPresenter(this);

        accountEntity = new AccountEntity();
        bindDataToView();
    }

    @Override
    public void start() {
        mRepertory.getAreaCodes(this);
    }

    @Override
    public void bindDataToView() {
        if (!mView.isActive()) return;
        mView.setAccountEntity(accountEntity);
    }

    @Override
    public void destroy() {
        if (mRepertory != null) mRepertory.destroy();
    }

    @Override
    public void onContinueClick() {
        LogUtils.d(this, accountEntity.getAccount());
        if (!PatternUtils.isMatchPhoneNumber(accountEntity.getAccount())) {
            mView.showShortToast("请输入正确手机号");
            return;
        }
    }

    @Override
    public void onDataLoaded(List<AreaCodeEntity> data) {
        accountEntity.setAreaCodes((ArrayList<AreaCodeEntity>) data);
        accountEntity.setAreaCode(data.get(0).getCode());
    }

    @Override
    public void onDataNotAvailable(String errData) {
        bindDataToView();
    }
}
