package com.jkb.commonlib.ui.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * window属性的注解
 * 支持：
 * 1、是否全屏
 * 2、是否沉浸式状态栏
 * Created by yangjing on 17-6-5.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface SupportWindow {
    /**
     * 是否全屏
     */
    boolean fullScreen() default false;

    /**
     * 是否沉浸式状态栏
     */
    boolean immersiveStatus() default false;
}
