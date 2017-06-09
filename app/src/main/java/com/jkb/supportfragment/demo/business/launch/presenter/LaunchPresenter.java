package com.jkb.supportfragment.demo.business.launch.presenter;

import com.jkb.commonlib.app.AppManager;
import com.jkb.commonlib.base.frame.BaseModel;
import com.jkb.supportfragment.demo.R;
import com.jkb.supportfragment.demo.entity.launch.LaunchEntity;
import com.jkb.supportfragment.demo.business.launch.contract.LaunchContract;
import com.jkb.supportfragment.demo.business.launch.model.LaunchDataRepertory;

/**
 * 启动页：Presenter
 * Created by yj on 2017/5/5.
 */

public class LaunchPresenter implements LaunchContract.Presenter, BaseModel.LoadDataCallBack<String> {

    private LaunchContract.View mView;
    private LaunchDataRepertory mRepertory;

    //data
    private LaunchEntity launchEntity;

    public LaunchPresenter(LaunchContract.View view, LaunchDataRepertory repertory) {
        mView = view;
        mRepertory = repertory;
        mView.setPresenter(this);

        launchEntity = new LaunchEntity();
        launchEntity.setLaunchPicture(R.drawable.img_launch);

        bindDataToView();
    }

    @Override
    public void start() {
        mRepertory.getLastUseVersion(this);
    }

    @Override
    public void bindDataToView() {
        if (!mView.isActive()) return;
        mView.setLaunchEntity(launchEntity);
    }

    @Override
    public void destroy() {
        if (mRepertory != null) mRepertory.destroy();
    }

    @Override
    public void onDataLoaded(String data) {
        if (!mView.isActive()) return;
        if (AppManager.getInstance().getSystemVersion().equals(data)) {
            if (AppManager.getInstance().isLogin()) {
                mView.launchMain();
            } else {
                mView.launchAccount();
            }
        } else {
            mRepertory.updateUseVersion();
            mView.launchOnBoarding();
        }
    }

    @Override
    public void onDataNotAvailable(String errData) {
        if (!mView.isActive()) return;
        mRepertory.updateUseVersion();
        mView.launchOnBoarding();
    }
}
