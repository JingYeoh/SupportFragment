package com.jkb.commonlib.ui.injection;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;

import com.jkb.commonlib.ui.annotation.SupportWindow;

/**
 * Support框架：window的注入器
 * 和注解{@link SupportWindow} 配合使用
 * Created by yangjing on 17-6-5.
 */

public class SupportWindowInjection {
    /**
     * 注入Window属性
     *
     * @param activity Activity
     */
    public static void injectWindow(@NonNull Object object, @NonNull Activity activity) {
        //1、使用类加载器加载类
        Class<?> clazz = object.getClass();
        //2.找到类注解
        SupportWindow supportWindow = clazz.getAnnotation(SupportWindow.class);
        if (supportWindow == null) return;
        Window window = activity.getWindow();
        //设置是否全屏
        if (supportWindow.fullScreen()) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams
                    .FLAG_FORCE_NOT_FULLSCREEN);
        }
        //设置是否沉浸式
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return;
        if (!supportWindow.fullScreen() && supportWindow.immersiveStatus()) {
            int flag_translucent_status = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            //透明状态栏
            window.setFlags(flag_translucent_status, flag_translucent_status);
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
