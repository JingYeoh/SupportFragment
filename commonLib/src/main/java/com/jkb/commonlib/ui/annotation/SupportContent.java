package com.jkb.commonlib.ui.annotation;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Support:設置contentView的註解類
 * 支持contentVIew和titleView
 * Created by yangjing on 17-6-5.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface SupportContent {
    /**
     * toolbar的布局id
     */
    @LayoutRes
    int toolBarViewId() default 0;

    /**
     * contentView的布局id
     */
    @LayoutRes
    int contentViewId() default 0;
}
