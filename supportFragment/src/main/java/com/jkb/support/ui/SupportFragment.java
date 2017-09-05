package com.jkb.support.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.jkb.support.ui.action.ISupportFragment;
import com.jkb.support.utils.SLogUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * 支持Support架构的Fragment
 * Created by JustKiddingBaby on 2017/4/15.
 */

public class SupportFragment extends Fragment implements ISupportFragment, ISupportAction {
    //tag
    protected String TAG = getClass().getSimpleName();
    //android data
    protected Context mContext;
    protected Context mApplicationContext;
    protected FragmentManager mChildFm;//自己的FM
    protected FragmentManager mParentFm;//父级FM
    //support data
    protected SupportActivity mActivity;
    private String mFragmentTag;//自己的标签
    private LinkedList<SupportManager.Builder> mFragmentTransactions;//事物的操作类
    private SupportStack mSupportStack;//内部维护的栈，用户Fragment的回退等操作
    //constant
    private static final String KEY_SAVED_FRAGMENT_IS_HIDDEN = "key.saved.fragment.isHidden";
    private static final String KEY_SAVED_FRAGMENT_TAG = "key.saved.fragment.tag";
    private static final String KEY_SAVED_IS_START_FOR_RESULT = "key.saved.isStartForResult";
    //startForResult
    private int mRequestCode;
    private boolean isStartForResult = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChildFm = getChildFragmentManager();
        mParentFm = getFragmentManager();
        if (mFragmentTransactions == null) {
            mFragmentTransactions = new LinkedList<>();
        }
        if (savedInstanceState != null) {
            restoreHiddenStatus(savedInstanceState);
            restoreFragmentStack(savedInstanceState);
        } else {
            mSupportStack = new SupportStack();
        }
        initRequestByStartForResult(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (SupportActivity) context;
        mContext = context;
        mApplicationContext = context.getApplicationContext();
    }

    @Override
    public void onResume() {
        super.onResume();
        //如果有未提交的事物则提交事物，防止Can not perform this action after onSaveInstanceState异常的发生
        while (true) {
            SupportManager.Builder poll = mFragmentTransactions.poll();
            if (poll == null) break;
            commitFragmentTransaction(poll);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_SAVED_FRAGMENT_IS_HIDDEN, isHidden());
        outState.putString(KEY_SAVED_FRAGMENT_TAG, mFragmentTag);
        outState.putSerializable(SupportStack.KEY_SAVED_SUPPORT_STACK, mSupportStack);
        outState.putBoolean(KEY_SAVED_IS_START_FOR_RESULT, isStartForResult);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mChildFm = null;
        mParentFm = null;
        mContext = null;
        mApplicationContext = null;
        mActivity = null;
        mSupportStack = null;
    }

    /**
     * 恢复Fragment是否隐藏的状态
     */
    private void restoreHiddenStatus(Bundle savedInstanceState) {
        boolean isHidden = savedInstanceState.getBoolean(KEY_SAVED_FRAGMENT_IS_HIDDEN);
        if (isHidden) {
            commitFragmentTransaction(SupportManager.beginTransaction(mParentFm).hide(this));
        } else {
            commitFragmentTransaction(SupportManager.beginTransaction(mParentFm).show(this));
        }
    }

    /**
     * 恢复Fragment栈
     */
    private void restoreFragmentStack(Bundle savedInstanceState) {
        mFragmentTag = savedInstanceState.getString(KEY_SAVED_FRAGMENT_TAG);
        mSupportStack = (SupportStack) savedInstanceState.getSerializable(SupportStack.KEY_SAVED_SUPPORT_STACK);
    }

    /**
     * 初始化通过StartForResult方法请求的数据
     */
    private void initRequestByStartForResult(Bundle savedInstanceState) {
        Bundle args = savedInstanceState;
        if (savedInstanceState == null) {
            args = getArguments();
            isStartForResult = args.getBundle(KEY_BUNDLE_FRAGMENT_RESULT) != null;
        } else {
            isStartForResult = args.getBoolean(KEY_SAVED_IS_START_FOR_RESULT);
        }
        mRequestCode = args.getInt(KEY_BUNDLE_FRAGMENT_REQUEST_CODE);
    }

    @Override
    public void onBackPressed() {
        SLogUtils.d(TAG, "onBackPressed");
        if (!mActivity.isHideLashFragmentBeforeFinish()) {
            if (mActivity.getSupportStack().getSupportStack().size() <= 1) {
                mActivity.onBackPressed();
                return;
            }
        }
        String popFragmentTag = mSupportStack.peek();
        if (TextUtils.isEmpty(popFragmentTag)) {
            closeCurrentAndShowPopFragment();
            return;
        }
        Fragment popFragment = SupportManager.getFragment(mChildFm, popFragmentTag);
        if (popFragment == null) {
            throwException(new NotFoundException(popFragmentTag));
        }
        if (popFragment instanceof SupportFragment) {
            ((SupportFragment) popFragment).onBackPressed();//委托给栈顶的Fragment执行OnBackPressed
        } else {
            throwException(new NotSupportException(popFragmentTag));
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String popFragmentTag = mSupportStack.peek();
        //栈为空时退出Activity
        if (TextUtils.isEmpty(popFragmentTag)) {
            close();
            return false;
        }
        Fragment popFragment = SupportManager.getFragment(mChildFm, popFragmentTag);
        if (popFragment == null) {//找不到Fragment的时候抛出异常
            throwException(new NotFoundException(popFragmentTag));
        }
        if (popFragment instanceof SupportFragment) {
            ((SupportFragment) popFragment).onKeyDown(keyCode, event);//委托给栈顶的Fragment
        } else {
            throwException(new NotSupportException(popFragmentTag));
        }
        return false;
    }

    @Override
    public String getFragmentTAG() {
        if (TextUtils.isEmpty(mFragmentTag)) {
            mFragmentTag = UUID.randomUUID().toString();
        }
        return mFragmentTag;
    }

    @Override
    final public int getRequestCode() {
        return mRequestCode;
    }

    @Override
    public boolean isSupportResumed() {
        return isResumed();
    }

    @Override
    public final void startFragment(@NonNull SupportFragment fragment) {
        mActivity.startFragment(fragment);
    }

    @Override
    public void startFragmentForResult(@NonNull SupportFragment fragment, int requestCode) {
        startFragmentForResult(fragment, requestCode, null);
    }

    @Override
    public void startFragmentForResult(@NonNull SupportFragment fragment, int requestCode, Bundle bundle) {
        mActivity.startFragmentForResult(fragment, requestCode, bundle);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle bundle) {

    }

    @Override
    public void setFragmentResult(int resultCode, Bundle bundle) {
        mActivity.setFragmentResult(resultCode, bundle);
    }

    @Override
    public void close() {
        if (isStartForResult) {
            mActivity.setFragmentResult(RESULT_CANCELED_FRAGMENT, null);
        }
        clearFragments();
        Fragment parentFragment = getParentFragment();
        if (parentFragment == null) {//由Activity直接添加的
            mActivity.closeFragment(this);
            return;
        }
        if (parentFragment instanceof SupportFragment) {
            ((SupportFragment) parentFragment).closeFragment(this);//被Fragment嵌套
        } else {
            throwException(new NotSupportException(parentFragment.getClass().getSimpleName()));
        }
    }

    @Override
    public final void closeFragment(@NonNull SupportFragment fragment) {
        if (!mSupportStack.remove(fragment.getFragmentTAG())) {
            throwException(new NotAddedException(fragment.getFragmentTAG()));
        } else {
            commitFragmentTransaction(SupportManager.beginTransaction(mChildFm).remove(fragment));
        }
    }

    @Override
    public final void showFragment(@NonNull SupportFragment fragment, @IdRes int contentId) {
        List<String> fragmentTags = mSupportStack.getFragmentTagsByContentId(contentId);
        for (String fragmentTag : fragmentTags) {//隐藏得到的所有Fragment
            Fragment hideFragment = SupportManager.getFragment(mChildFm, fragmentTag);
            if (hideFragment == null) {
                throwException(new NotAddedException(fragmentTag));
                continue;
            }
            commitFragmentTransaction(SupportManager.beginTransaction(mChildFm).hide(hideFragment));
        }
        //添加并显示该Fragment
        if (mSupportStack.push(fragment.getFragmentTAG(), contentId)) {
            commitFragmentTransaction(SupportManager.beginTransaction(mChildFm).add(fragment, contentId).show
                    (fragment));
        } else {//只显示Fragment
            commitFragmentTransaction(SupportManager.beginTransaction(mChildFm).show(fragment));
        }
    }

    @Override
    public void hideFragment(@NonNull SupportFragment fragment) {
        if (mSupportStack.isFragmentInStack(fragment.getFragmentTAG())) {
            commitFragmentTransaction(SupportManager.beginTransaction(mChildFm).hide(fragment));
        } else {
            throwException(new NotAddedException(fragment.getFragmentTAG()));
        }
    }

    @Override
    public final void replaceFragment(@NonNull SupportFragment fragment, @IdRes int contentId) {
        List<String> fragmentTags = mSupportStack.getFragmentTagsByContentId(contentId);
        for (String fragmentTag : fragmentTags) {//隐藏得到的所有Fragment
            Fragment hideFragment = SupportManager.getFragment(mChildFm, fragmentTag);
            if (hideFragment == null) {
                throwException(new NotAddedException(fragmentTag));
                continue;
            }
            mSupportStack.remove(fragmentTag);
            commitFragmentTransaction(SupportManager.beginTransaction(mChildFm).remove(hideFragment));
        }
        //添加并显示该Fragment
        if (mSupportStack.push(fragment.getFragmentTAG(), contentId)) {
            commitFragmentTransaction(SupportManager.beginTransaction(mChildFm).add(fragment, contentId).show
                    (fragment));
        } else {
            throwException(new HasBeenAddedException(fragment.getFragmentTAG()));
        }
    }

    @Override
    public final void showPopFragment() {
        //显示栈顶页面
        String popFragmentTag = mSupportStack.peek();
        if (TextUtils.isEmpty(popFragmentTag)) {
            onBackPressed();//栈为空时交给onBackPressed处理
            return;
        }
        Fragment popFragment = SupportManager.getFragment(mChildFm, popFragmentTag);
        if (popFragment == null) {
            throwException(new NotAddedException(popFragmentTag));
        }
        if (popFragment instanceof SupportFragment) {
            commitFragmentTransaction(SupportManager.beginTransaction(mChildFm).hideAll().show(popFragment));
        } else {
            throwException(new NotSupportException(popFragmentTag));
        }
    }

    @Override
    public final void closeCurrentAndShowPopFragment() {
        close();//关闭当前页面
        Fragment parentFragment = getParentFragment();
        if (parentFragment == null) {
            mActivity.showPopFragment();
            return;
        }
        if (parentFragment instanceof SupportFragment) {
            ((SupportFragment) parentFragment).showPopFragment();//被Fragment嵌套
        } else {
            throwException(new NotSupportException(parentFragment.getClass().getSimpleName()));
        }
    }

    @Override
    public final void clearFragments() {
        mSupportStack.clear();
        commitFragmentTransaction(SupportManager.beginTransaction(mChildFm).removeAll());
    }

    @Override
    public boolean isContainChildFragment() {
        return !TextUtils.isEmpty(mSupportStack.peek());
    }

    @Override
    public SupportFragment getPopSupportFragment() {
        String popFragmentTag = mSupportStack.peek();
        if (TextUtils.isEmpty(popFragmentTag)) return null;
        Fragment popFragment = SupportManager.getFragment(mChildFm, popFragmentTag);
        if (popFragment != null && popFragment instanceof SupportFragment) return (SupportFragment) popFragment;
        return null;
    }

    @NonNull
    @Override
    public SupportStack getSupportStack() {
        return mSupportStack;
    }

    /**
     * 提交事物
     */
    private void commitFragmentTransaction(@NonNull SupportManager.Builder execute) {
        if (!isResumed()) {
            mFragmentTransactions.add(execute);
            SLogUtils.w(TAG, "commitFragmentTransaction------>Activity is not resumed,this commit is delayed");
            return;
        }
        SLogUtils.i(TAG, "commitFragmentTransaction------>This transaction will be commit");
        execute.commit();
    }

    /**
     * 抛出Support可能遇到的异常
     */
    public void throwException(SupportException e) {
        throw e;
    }
}
