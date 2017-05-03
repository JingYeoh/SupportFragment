package com.jkb.support.helper;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jkb.support.ui.SupportFragment;

import java.util.List;

/**
 * 该Support架构的Fragment管理类
 * 作用：通过TAG获取Fragment对象；获取Fragment的TAG；执行Fragment事物操作；判断Fragment状态等
 * 设计模式：Builder模式
 * Created by JustKiddingBaby on 2017/4/15.
 */

public class SupportManager {

    private SupportManager() {
    }

    public static Builder beginTransaction(FragmentManager fm) {
        return new Builder(fm);
    }

    /**
     * 执行具体事物操作的Builder内部类
     */
    public static class Builder {

        private FragmentManager fm;
        private FragmentTransaction ft;

        private Builder(android.support.v4.app.FragmentManager fm) {
            this.fm = fm;
            ft = fm.beginTransaction();
        }

        /**
         * 提交
         */
        public void commit() {
            ft.commit();
            ft = null;
            fm = null;
        }

        /**
         * 添加Fragment
         */
        public Builder add(@NonNull Fragment fragment, @IdRes int contentId) {
            ft.add(contentId, fragment, getFragmentTAG(fragment));
            return this;
        }

        /**
         * 移除Fragment
         */
        public Builder remove(@NonNull Fragment fragment) {
            ft.detach(fragment);
            ft.remove(fragment);
            return this;
        }

        /**
         * 移除所有的Fragment
         */
        public Builder removeAll() {
            List<Fragment> fragments = fm.getFragments();
            if (fragments == null || fragments.size() == 0) {
                return this;
            }
            for (Fragment fragment : fragments) {
                if (isFragmentAdded(fm, fragment)) {
                    remove(fragment);
                }
            }
            return this;
        }

        /**
         * 显示Fragment
         */
        public Builder show(@NonNull Fragment fragment) {
            ft.show(fragment);
            return this;
        }

        /**
         * 隐藏Fragment
         */
        public Builder hide(@NonNull Fragment fragment) {
            ft.hide(fragment);
            return this;
        }

        /**
         * 隐藏所有的Fragment
         */
        public Builder hideAll() {
            List<Fragment> fragments = fm.getFragments();
            if (fragments == null) {
                return this;
            }
            for (Fragment fragment : fragments) {
                if (fragment == null) {
                    continue;
                }
                hide(fragment);
            }
            return this;
        }
    }

    /**
     * 得到Fragment
     */
    public static Fragment getFragment(@NonNull FragmentManager fm, @NonNull String fragmentTag) {
        return fm.findFragmentByTag(fragmentTag);
    }

    /**
     * 检查Fragment是否被添加过
     */
    public static boolean isFragmentAdded(@NonNull FragmentManager fm, Fragment fragment) {
        if (fragment == null) {
            return false;
        }
        Fragment fragmentByTag = fm.findFragmentByTag(getFragmentTAG(fragment));
        return fragmentByTag != null && fragmentByTag.isAdded();
    }

    /**
     * 得到Fragment的Tag
     */
    public static String getFragmentTAG(@NonNull Fragment fragment) {
        if (fragment instanceof SupportFragment) {
            return ((SupportFragment) fragment).getFragmentTAG();
        }
        return fragment.getClass().getSimpleName();
    }
}
