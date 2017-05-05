package com.jkb.support.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.jkb.support.expecption.HasBeenAddedException;
import com.jkb.support.expecption.NotAddedException;
import com.jkb.support.expecption.NotFoundException;
import com.jkb.support.expecption.NotSupportException;
import com.jkb.support.expecption.SupportException;
import com.jkb.support.helper.SupportManager;
import com.jkb.support.helper.SupportStack;
import com.jkb.support.ui.action.ISupportAction;
import com.jkb.support.ui.action.ISupportActivity;

import java.util.List;

/**
 * 支持Support架构的Activity
 * Created by JustKiddingBaby on 2017/4/15.
 */

public abstract class SupportActivity extends AppCompatActivity implements ISupportActivity, ISupportAction {

    //tag
    protected String TAG = getClass().getSimpleName();
    //support data
    private SupportStack mSupportStack;
    protected FragmentManager mFm;
    private SupportManager.Builder mFragmentExecute;//事物的操作类
    private boolean isResumed = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFm = getSupportFragmentManager();
        if (savedInstanceState == null) {
            mSupportStack = new SupportStack();
        } else {
            mSupportStack = (SupportStack) savedInstanceState.getSerializable(SupportStack.KEY_SAVED_SUPPORT_STACK);
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        isResumed = true;
        //如果有未提交的事物则提交事物，防止Can not perform this action after onSaveInstanceState异常的发生
        if (mFragmentExecute != null) {
            commitFragmentTransaction(mFragmentExecute);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SupportStack.KEY_SAVED_SUPPORT_STACK, mSupportStack);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResumed = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSupportStack = null;
    }

    @Override
    public final void startFragment(@NonNull SupportFragment fragment) {
        if (mSupportStack.push(fragment.getFragmentTAG(), getFragmentContentId())) {
            commitFragmentTransaction(SupportManager.beginTransaction(mFm).add(fragment, getFragmentContentId())
                    .hideAll().show(fragment));
        } else {
            throwException(new HasBeenAddedException(fragment.getFragmentTAG()));
        }
    }

    @Override
    public void close() {
        clearFragments();
        finish();
    }

    @Override
    public final void closeFragment(@NonNull SupportFragment fragment) {
        if (!mSupportStack.remove(fragment.getFragmentTAG())) {
            throwException(new NotAddedException(fragment.getFragmentTAG()));
        }
        commitFragmentTransaction(SupportManager.beginTransaction(mFm).remove(fragment));
    }

    @Override
    public final void showFragment(@NonNull SupportFragment fragment, @IdRes int contentId) {
        List<String> fragmentTags = mSupportStack.getFragmentTagsByContentId(contentId);
        for (String fragmentTag : fragmentTags) {//隐藏得到的所有Fragment
            Fragment hideFragment = SupportManager.getFragment(mFm, fragmentTag);
            if (hideFragment == null) {
                throwException(new NotAddedException(fragmentTag));
                continue;
            }
            commitFragmentTransaction(SupportManager.beginTransaction(mFm).hide(hideFragment));
        }
        //添加并显示该Fragment
        if (mSupportStack.push(fragment.getFragmentTAG(), contentId)) {
            commitFragmentTransaction(SupportManager.beginTransaction(mFm).add(fragment, contentId).show
                    (fragment));
        } else {
            commitFragmentTransaction(SupportManager.beginTransaction(mFm).show(fragment));
//            throwException(new HasBeenAddedException(fragment.getFragmentTAG()));
        }
    }

    @Override
    public final void replaceFragment(@NonNull SupportFragment fragment, @IdRes int contentId) {
        List<String> fragmentTags = mSupportStack.getFragmentTagsByContentId(contentId);
        for (String fragmentTag : fragmentTags) {//隐藏得到的所有Fragment
            Fragment hideFragment = SupportManager.getFragment(mFm, fragmentTag);
            if (hideFragment == null) {
                throwException(new NotAddedException(fragmentTag));
                continue;
            }
            mSupportStack.remove(fragmentTag);
            commitFragmentTransaction(SupportManager.beginTransaction(mFm).remove(hideFragment));
        }
        //添加并显示该Fragment
        if (mSupportStack.push(fragment.getFragmentTAG(), contentId)) {
            commitFragmentTransaction(SupportManager.beginTransaction(mFm).add(fragment, contentId).show
                    (fragment));
        } else {
            throwException(new HasBeenAddedException(fragment.getFragmentTAG()));
        }
    }

    @Override
    public final void showPopFragment() {
        //显示栈顶的视图
        String popFragmentTag = mSupportStack.peek();
        if (TextUtils.isEmpty(popFragmentTag)) {//此处只会被子Fragment调用
            close();
            return;
        }
        Fragment popFragment = SupportManager.getFragment(mFm, popFragmentTag);
        if (popFragment != null) {
            commitFragmentTransaction(SupportManager.beginTransaction(mFm).hideAll().show(popFragment));
        } else {
            throwException(new NotAddedException(popFragmentTag));
        }
    }

    @Override
    public final void closeCurrentAndShowPopFragment() {
        close();
        showPopFragment();
    }

    @Override
    public final void clearFragments() {
        mSupportStack.clear();
        commitFragmentTransaction(SupportManager.beginTransaction(mFm).removeAll());
    }

    @Override
    public void onBackPressed() {
        String popFragmentTag = mSupportStack.peek();
        //栈为空时退出Activity
        if (TextUtils.isEmpty(popFragmentTag)) {
            close();
            return;
        }
        Fragment popFragment = SupportManager.getFragment(mFm, popFragmentTag);
        if (popFragment == null) {//找不到Fragment的时候抛出异常
            throwException(new NotFoundException(popFragmentTag));
        }
        if (popFragment instanceof SupportFragment) {
            ((SupportFragment) popFragment).onBackPressed();//委托给栈顶的Fragment
        } else {
            throwException(new NotSupportException(popFragmentTag));
        }
    }

    /**
     * 抛出Support可能遇到的异常
     */
    public void throwException(SupportException e) {
        throw e;
    }

    /**
     * 提交事物
     */
    private void commitFragmentTransaction(@NonNull SupportManager.Builder execute) {
        mFragmentExecute = execute;
        if (!isResumed) {
            return;
        }
        mFragmentExecute.commit();
        mFragmentExecute = null;
    }
}
