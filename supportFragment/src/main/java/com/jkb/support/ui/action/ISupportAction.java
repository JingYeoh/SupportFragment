package com.jkb.support.ui.action;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import com.jkb.support.ui.SupportFragment;

/**
 * 该Support架构可用的支持Action的封装
 * Created by JustKiddingBaby on 2017/4/15.
 */

public interface ISupportAction {

    /**
     * Standard fragment result: operation canceled.
     */
    int RESULT_CANCELED_FRAGMENT = 0;
    /**
     * Standard fragment result: operation succeeded.
     */
    int RESULT_OK_FRAGMENT = -1;
    /**
     * Fragment返回结果的KEY
     */
    String KEY_BUNDLE_FRAGMENT_RESULT = "key.bundle.fragment.result";
    /**
     * Fragment返回结果码的KEY
     */
    String KEY_BUNDLE_FRAGMENT_REQUEST_CODE = "key.bundle.fragment.requestCode";

    /**
     * 是否处于Resume状态.
     */
    boolean isSupportResumed();

    /**
     * 打开新页面
     * Action:隐藏该Activity的所有Fragment
     */
    void startFragment(@NonNull SupportFragment fragment);

    /**
     * 打开Fragment并返回值
     *
     * @param fragment    需要打开的Fragment
     * @param requestCode 请求码
     */
    void startFragmentForResult(@NonNull SupportFragment fragment, int requestCode);

    /**
     * 打开Fragment并返回值
     *
     * @param fragment    需要打开的Fragment
     * @param requestCode 请求码
     * @param bundle      需要传递的参数
     */
    void startFragmentForResult(@NonNull SupportFragment fragment, int requestCode, Bundle bundle);

    /**
     * 返回Result，和startFragmentForResult方法一起使用
     *
     * @param requestCode 请求码
     * @param resultCode  返回码
     * @param bundle      参数
     */
    void onFragmentResult(int requestCode, int resultCode, Bundle bundle);

    /**
     * 设置返回结果
     *
     * @param resultCode 返回码
     * @param bundle     参数
     */
    void setFragmentResult(int resultCode, Bundle bundle);

    /**
     * 关闭本页面
     * 被动执行：自己的栈为空时OnBackPressed时调用
     * 主动执行：关闭当前页面并递归清空栈内成员
     * Action:close的逻辑判断
     */
    void close();

    /**
     * 关闭指定页面
     * Action:具体close的执行方法，关闭自己并清空栈
     */
    void closeFragment(@NonNull SupportFragment fragment);

    /**
     * 显示Fragment，用于Fragment的嵌套
     * Action:隐藏之前contentId中的Fragment，并显示fragment
     * Support:栈的回退显示
     */
    void showFragment(@NonNull SupportFragment fragment, @IdRes int contentId);

    /**
     * 隐藏Fragment的显示，不销毁Fragment
     * Action:保存Fragment栈的顺序
     */
    void hideFragment(@NonNull SupportFragment fragment);

    /**
     * 替换contentId中的Fragment并显示，用于Fragment的嵌套
     * Action:销毁之前contentId上的Fragment，只显示fragment
     */
    void replaceFragment(@NonNull SupportFragment fragment, @IdRes int contentId);

    /**
     * 显示栈顶页面
     * Action：显示栈顶的页面，不进行其他处理
     */
    void showPopFragment();

    /**
     * 关闭当前页面并显示上级页面
     * 使用场景：当前栈为空时，OnBackPressed时调用
     */
    void closeCurrentAndShowPopFragment();

    /**
     * 清空栈中的所有Fragment
     */
    void clearFragments();

    /**
     * 是否包含子Fragment
     *
     * @return true表示该Fragment为最内层Fragment, 否则该Fragment还有子Fragment.
     */
    boolean isContainChildFragment();
}
