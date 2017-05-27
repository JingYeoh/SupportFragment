package com.jkb.supportfragment.demo.business.auth.register.presenter;

import com.jkb.commonlib.app.AppManager;
import com.jkb.commonlib.base.frame.BaseModel;
import com.jkb.commonlib.base.frame.i.IBasePresenter;
import com.jkb.commonlib.db.entity.User;
import com.jkb.supportfragment.demo.business.auth.register.contract.RegisterContract;
import com.jkb.supportfragment.demo.business.auth.register.model.RegisterDataRepertory;
import com.jkb.supportfragment.demo.entity.auth.RegisterEntity;
import com.jkb.supportfragment.demo.net.entity.PictureAttribute;

/**
 * 注册：Presenter
 * Created by yj on 2017/5/12.
 */

public class RegisterPresenter extends IBasePresenter<RegisterContract.View, RegisterDataRepertory> implements
        RegisterContract.Presenter {

    private RegisterEntity registerEntity;

    public RegisterPresenter(RegisterContract.View mView, RegisterDataRepertory mRepertory) {
        super(mView, mRepertory);

        registerEntity = new RegisterEntity();
        bindDataToView();
    }

    @Override
    public void start() {

    }

    @Override
    public void bindDataToView() {
        if (!isViewActive()) return;
        mView.bindPasswordEntity(registerEntity);
    }

    @Override
    public void register() {
        mRepertory.register(registerEntity, registerCallback);
    }

    @Override
    public void uploadPicture(String path) {
        mRepertory.uploadPicture(path, uploadCallback);
    }

    /**
     * 上传的回调
     */
    private BaseModel.LoadDataCallBack<PictureAttribute> uploadCallback =
            new BaseModel.LoadDataCallBack<PictureAttribute>() {
                @Override
                public void onDataLoaded(PictureAttribute data) {
                    registerEntity.setAvatarUrl(data.getPhotoPath());
                }

                @Override
                public void onDataNotAvailable(String errData) {
                    showShortToast(errData);
                }
            };

    /**
     * 注册回调
     */
    private BaseModel.LoadDataCallBack<User> registerCallback = new BaseModel.LoadDataCallBack<User>() {
        @Override
        public void onDataLoaded(User data) {
            if (isViewActive()) {
                AppManager.getInstance().loginSystem(data.getUserId());
                mView.registerSuccess();
                mView.launchAppMain();
            }
        }

        @Override
        public void onDataNotAvailable(String errData) {
            showShortToast(errData);
        }
    };
}
