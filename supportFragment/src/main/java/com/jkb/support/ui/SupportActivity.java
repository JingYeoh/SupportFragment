package com.jkb.support.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.jkb.support.expecption.HasBeenAddedException;
import com.jkb.support.expecption.NotAddedException;
import com.jkb.support.expecption.NotFoundException;
import com.jkb.support.expecption.NotSupportException;
import com.jkb.support.expecption.SupportException;
import com.jkb.support.helper.SupportManager;
import com.jkb.support.helper.SupportStack;
import com.jkb.support.ui.action.ISupportAction;
import com.jkb.support.ui.action.ISupportActivity;
import com.jkb.support.utils.SLogUtils;

import java.util.LinkedList;
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
    private LinkedList<SupportManager.Builder> mFragmentTransactions;//事物的操作类
    private boolean isResumed = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFm = getSupportFragmentManager();
        if (mFragmentTransactions == null) {
            mFragmentTransactions = new LinkedList<>();
        }
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
        while (true) {
            SupportManager.Builder poll = mFragmentTransactions.poll();
            if (poll == null) break;
            commitFragmentTransaction(poll);
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        isResumed = true;
        //如果有未提交的事物则提交事物，防止Can not perform this action after onSaveInstanceState异常的发生
        while (true) {
            SupportManager.Builder poll = mFragmentTransactions.poll();
            if (poll == null) break;
            commitFragmentTransaction(poll);
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
    public boolean isSupportResumed() {
        return isResumed;
    }

    @Override
    public final void startFragment(@NonNull SupportFragment fragment) {
        SLogUtils.i(TAG, "startFragment--->fragment=" + fragment.getClass().getSimpleName());
        if (mSupportStack.push(fragment.getFragmentTAG(), getFragmentContentId())) {
            commitFragmentTransaction(SupportManager.beginTransaction(mFm).add(fragment, getFragmentContentId())
                    .hideAll().show(fragment));
        } else {
            throwException(new HasBeenAddedException(fragment.getFragmentTAG()));
        }
    }

    @Override
    public void startFragmentForResult(@NonNull SupportFragment fragment, int requestCode) {
        startFragmentForResult(fragment, requestCode, null);
    }

    @Override
    public void startFragmentForResult(@NonNull SupportFragment fragment, int requestCode, Bundle bundle) {
        Bundle args = fragment.getArguments();
        args.putInt(KEY_BUNDLE_FRAGMENT_REQUEST_CODE, requestCode);
        if (bundle != null) {
            args.putBundle(KEY_BUNDLE_FRAGMENT_RESULT, bundle);
        } else {
            args.putBundle(KEY_BUNDLE_FRAGMENT_RESULT, new Bundle());
        }
        startFragment(fragment);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle bundle) {

    }

    @Override
    public void setFragmentResult(int resultCode, Bundle bundle) {
        String fragmentTag = mSupportStack.getPenultimateFragmentTag();
        if (TextUtils.isEmpty(fragmentTag)) {
            throwException(new NotFoundException("the fragment that call this method is might closed,this is not " +
                    "allowed"));
        } else {
            Fragment fragment = SupportManager.getFragment(mFm, fragmentTag);
            if (fragment instanceof SupportFragment) {
                ((SupportFragment) fragment).onFragmentResult(((SupportFragment) fragment).getRequestCode(),
                        resultCode, bundle);
            } else {
                throwException(new NotSupportException(fragment.getClass().getSimpleName()));
            }
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
        }
    }

    @Override
    public void hideFragment(@NonNull SupportFragment fragment) {
        if (mSupportStack.isFragmentInStack(fragment.getFragmentTAG())) {
            commitFragmentTransaction(SupportManager.beginTransaction(mFm).hide(fragment));
        } else {
            throwException(new NotAddedException(fragment.getFragmentTAG()));
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
    public boolean isContainChildFragment() {
        return !TextUtils.isEmpty(mSupportStack.peek());
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
      /*  String popFragmentTag = mSupportStack.peek();
        //栈为空时退出Activity
        if (TextUtils.isEmpty(popFragmentTag)) {
            close();
            return super.onKeyDown(keyCode, event);
        }
        Fragment popFragment = SupportManager.getFragment(mFm, popFragmentTag);
        if (popFragment == null) {//找不到Fragment的时候抛出异常
            throwException(new NotFoundException(popFragmentTag));
        }
        if (popFragment instanceof SupportFragment) {
            ((SupportFragment) popFragment).onKeyDown(keyCode, event);//委托给栈顶的Fragment
        } else {
            throwException(new NotSupportException(popFragmentTag));
        }*/
        return super.onKeyDown(keyCode, event);
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
        if (!isResumed) {
            mFragmentTransactions.add(execute);
            SLogUtils.w(TAG, "commitFragmentTransaction------>Activity is not resumed,this commit is delayed");
            return;
        }
        SLogUtils.i(TAG, "commitFragmentTransaction------>This transaction will be commit");
        execute.commit();
    }
}
