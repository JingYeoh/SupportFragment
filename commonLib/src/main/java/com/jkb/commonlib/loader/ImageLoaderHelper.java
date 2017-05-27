package com.jkb.commonlib.loader;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jkb.commonlib.app.AppManager;

/**
 * 图像加载类
 * Created by yj on 2017/5/27.
 */

public class ImageLoaderHelper {
    /**
     * 加载图片
     *
     * @param context   上下文
     * @param path      图片路径
     * @param errorRes  失败展位图
     * @param imageView 要显示的ImageView
     */
    public static void displayImage(Context context, String path, int errorRes, ImageView imageView) {
        DrawableRequestBuilder builder;
        builder = Glide.with(context)
                .load(path);
        builder = builder.centerCrop();
        builder.crossFade()
                .error(ContextCompat.getDrawable(context, errorRes))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
        builder.crossFade();
        if (errorRes > 0) {
            builder.error(errorRes);
        }
        builder.diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    /**
     * 加载图片
     *
     * @param path      图片路径
     * @param errorRes  失败展位图
     * @param imageView 要显示的ImageView
     */
    public static void displayImage(String path, int errorRes, ImageView imageView) {
        Context context = AppManager.getInstance().getApplicationContext();
        displayImage(context, path, errorRes, imageView);
    }

    /**
     * 加载图片
     *
     * @param path      图片路径
     * @param imageView 要显示的ImageView
     */
    public static void displayImage(String path, ImageView imageView) {
        displayImage(path, 0, imageView);
    }
}
