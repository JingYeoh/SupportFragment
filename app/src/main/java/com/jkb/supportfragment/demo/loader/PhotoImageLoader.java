package com.jkb.supportfragment.demo.loader;

import android.content.Context;
import android.widget.ImageView;

import com.jkb.commonlib.loader.ImageLoaderHelper;
import com.jkb.support.photopicker.loader.ImageLoader;
import com.jkb.supportfragment.demo.R;

/**
 * 图片选择器的加载器
 * Created by yj on 2017/5/27.
 */

public class PhotoImageLoader implements ImageLoader {
    @Override
    public void displayImage(Context context, String path, ImageView imageView, boolean resize) {
        ImageLoaderHelper.displayImage(context, path, R.mipmap.error_image, imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
