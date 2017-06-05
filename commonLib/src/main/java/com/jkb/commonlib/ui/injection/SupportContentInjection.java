package com.jkb.commonlib.ui.injection;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jkb.commonlib.ui.annotation.SupportContent;

/**
 * Support:contentView的注入器
 * 爲{@link SupportContent}提供支持
 * Created by yangjing on 17-6-5.
 */

public class SupportContentInjection {

    /**
     * 注入ContentView相关属性
     *
     * @param activity Activity类
     * @return {@link View}创建完成的View对象，否则返回null
     */
    public static View injectContentView(@NonNull Object object, @NonNull AppCompatActivity activity) {
        //1、使用类加载器加载类
        Class<?> clazz = object.getClass();
        //2.找到类注解
        SupportContent supportContent = clazz.getAnnotation(SupportContent.class);
        if (supportContent == null) return null;
        return injectContentView(activity, supportContent.contentViewId(), supportContent.toolBarViewId());
    }

    /**
     * 注入ContentView相关属性
     *
     * @param activity      Activity类
     * @param contentViewId contentView的布局id，无则传0
     * @param toolbarViewId toolbar的布局id，无则传0
     * @return {@link View}创建完成的View对象，否则返回null
     */
    public static View injectContentView(@NonNull AppCompatActivity activity, @LayoutRes int contentViewId,
                                         @LayoutRes int toolbarViewId) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        ViewGroup.LayoutParams matchParentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        //3.1.新建LinearLayout作为ContentView根布局
        LinearLayout rootView = new LinearLayout(activity);
        rootView.setLayoutParams(matchParentParams);
        rootView.setOrientation(LinearLayout.VERTICAL);
        //3.3.添加content
        if (contentViewId != 0) {
            View contentView = inflater.inflate(contentViewId, null);
            rootView.addView(contentView, matchParentParams);
        }
        //3.2.添加toolbar
        if (toolbarViewId != 0) {
            View titleView = inflater.inflate(toolbarViewId, null);
            rootView.addView(titleView, 0);
        }
        return rootView;
    }

}
