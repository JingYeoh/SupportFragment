package com.jkb.supportfragment.demo.business.auth.verCode.presenter;

import com.jkb.commonlib.base.frame.BaseModel;
import com.jkb.supportfragment.demo.business.auth.verCode.contract.VerCodeContract;
import com.jkb.supportfragment.demo.business.auth.verCode.model.VerCodeDataRepertory;
import com.jkb.supportfragment.demo.entity.auth.VerCodeEntity;

/**
 * 验证码:Presenter
 * Created by yj on 2017/5/8.
 */

public class VerCodePresenter implements VerCodeContract.Presenter {

    private VerCodeContract.View mView;
    private VerCodeDataRepertory mRepertory;

    //data
    private VerCodeEntity verCodeEntity;

    public VerCodePresenter(VerCodeContract.View mView, VerCodeDataRepertory mRepertory) {
        this.mView = mView;
        this.mRepertory = mRepertory;
        this.mView.setPresenter(this);

        verCodeEntity = new VerCodeEntity();
        bindDataToView();
    }

    @Override
    public void start() {

    }

    @Override
    public void bindDataToView() {
        if (!mView.isActive()) return;
        mView.bindVerCodeEntity(verCodeEntity);
    }

    @Override
    public void destroy() {
        if (mRepertory != null) mRepertory.destroy();
    }

    @Override
    public void sendVerCode() {
        mRepertory.sendVerCode(verCodeEntity.getPhoneNumber(), sendVerCodeCallback);
    }

    /**
     * 发送验证码回调
     */
    private BaseModel.LoadDataCallBack<String> sendVerCodeCallback = new BaseModel.LoadDataCallBack<String>() {
        @Override
        public void onDataLoaded(String data) {
            if (!mView.isActive()) return;
            mView.showShortToast("您的验证码是：" + data);
            mView.startSmsCount();
        }

        @Override
        public void onDataNotAvailable(String errData) {
            if (!mView.isActive()) return;
            mView.showShortToast("errData");
        }
    };
}
