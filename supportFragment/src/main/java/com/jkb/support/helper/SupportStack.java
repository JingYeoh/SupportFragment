package com.jkb.support.helper;

import com.jkb.support.utils.LogUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 支持Support架构的栈
 * Created by JustKiddingBaby on 2017/4/15.
 */

public class SupportStack implements Cloneable, Serializable {

    public static final String KEY_SAVED_SUPPORT_STACK = "key.saved.support.stack";
    /*存放FragmentTAG的栈*/
    private Stack<String> mSupportStack;
    /*存放Fragment占位id的HashTable*/
    private HashMap<String, Integer> mSupportFragmentContent;

    public SupportStack() {
        mSupportStack = new Stack<>();
        mSupportFragmentContent = new HashMap<>();
    }

    /**
     * fragmentTag入栈
     *
     * @param fragmentTag Fragment的tag
     * @param contentId   Fragment所在布局的Id
     * @return 操作是否成功
     */
    public boolean push(String fragmentTag, int contentId) {
        if (!mSupportStack.contains(fragmentTag)) {
            mSupportStack.push(fragmentTag);
            mSupportFragmentContent.put(fragmentTag, contentId);
            return true;
        }
        return false;
    }


    /**
     * 返回栈顶的FragmentTag并进行栈顶元素出栈操作
     *
     * @return FragmentTag，空栈则返回null
     */
    public String pop() {
        String fragmentTag = null;
        if (!mSupportStack.empty()) {
            fragmentTag = mSupportStack.pop();
            mSupportFragmentContent.remove(fragmentTag);
        }
        return fragmentTag;
    }

    /**
     * 返回栈顶的FragmentTag
     *
     * @return FragmentTag，空栈则返回null
     */
    public String peek() {
        String fragmentTag = null;
        if (!mSupportStack.empty()) {
            fragmentTag = mSupportStack.peek();
        }
        return fragmentTag;
    }

    /**
     * 移除指定元素
     *
     * @param fragmentTag FragmentTag
     * @return 成功返回true
     */
    public boolean remove(String fragmentTag) {
        if (mSupportStack.contains(fragmentTag)) {
            mSupportStack.remove(fragmentTag);
            mSupportFragmentContent.remove(fragmentTag);
            return true;
        }
        return false;
    }

    /**
     * 返回contentId所属的所有FragmentTag
     *
     * @param contentId Fragment所属的id
     * @return 所有FragmentTag
     */
    public List<String> getFragmentTagsByContentId(int contentId) {
        List<String> fragmentTags = new ArrayList<>();
        Iterator<Map.Entry<String, Integer>> iterator = mSupportFragmentContent.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> item = iterator.next();
            if (item.getValue() == contentId) fragmentTags.add(item.getKey());
        }
        return fragmentTags;
    }

    /**
     * 返回倒数第二个栈内的Fragment
     */
    public String getPenultimateFragmentTag() {
        if (mSupportStack == null || mSupportStack.isEmpty() || mSupportStack.size() == 1) {
            return null;
        } else {
            return mSupportStack.get(mSupportStack.size() - 2);
        }
    }

    /**
     * Fragment是否被添到栈中
     *
     * @param fragmentTAG Fragment的TAG
     */
    public boolean isFragmentInStack(String fragmentTAG) {
        return !(mSupportStack == null || mSupportStack.isEmpty()) && mSupportStack.contains(fragmentTAG);
    }

    /**
     * 清空栈，在Destroy的时候使用，使用后该栈不可用
     */
    public void clear() {
        mSupportStack.clear();
        mSupportFragmentContent.clear();
    }

    /**
     * 打印栈
     */
    public void printStack() {
        for (String tag : mSupportStack) {
            LogUtils.i(this.getClass(), "tag=" + tag);
        }
    }
}
