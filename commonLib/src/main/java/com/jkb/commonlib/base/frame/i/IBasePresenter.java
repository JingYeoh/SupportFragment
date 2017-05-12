package com.jkb.commonlib.base.frame.i;

import android.text.TextUtils;

import com.jkb.commonlib.base.frame.BaseModel;
import com.jkb.commonlib.base.frame.BasePresenter;
import com.jkb.commonlib.base.frame.BaseView;

/**
 * Presenter的基类
 * Created by yj on 2017/5/12.
 */

public abstract class IBasePresenter<V extends BaseView, M extends BaseModel> implements BasePresenter {

    protected V mView;
    protected M mRepertory;

    public IBasePresenter(V view, M repertory) {
        this.mView = view;
        this.mRepertory = repertory;
        mView.setPresenter(this);
    }

    @Override
    public void destroy() {
        if (mRepertory != null) mRepertory.destroy();
    }

    /**
     * 显示Toast信息
     */
    protected void showShortToast(String value) {
        if (TextUtils.isEmpty(value)) return;
        if (!mView.isActive()) return;
        mView.showShortToast(value);
    }

    /**
     * View是否Active
     */
    protected boolean isViewActive() {
        return mView != null && mView.isActive();
    }
}
