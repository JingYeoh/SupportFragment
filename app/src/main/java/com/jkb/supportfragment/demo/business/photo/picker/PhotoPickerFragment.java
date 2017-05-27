package com.jkb.supportfragment.demo.business.photo.picker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.jkb.commonlib.base.ui.BaseFragment;
import com.jkb.support.helper.SupportManager;
import com.jkb.support.photopicker.bean.Photo;
import com.jkb.support.photopicker.business.pick.PhotoPickFragment;
import com.jkb.support.photopicker.business.pick.i.OnPhotoItemClickListener;
import com.jkb.support.photopicker.config.PhotoPicker;
import com.jkb.support.photopicker.utils.ImageUtils;
import com.jkb.support.photopicker.utils.UCropUtils;
import com.jkb.supportfragment.demo.R;
import com.jkb.supportfragment.demo.loader.PhotoImageLoader;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.ArrayList;

/**
 * 选择图片
 * 只支持单张图片
 * Created by yj on 2017/5/27.
 */

public class PhotoPickerFragment extends BaseFragment implements OnPhotoItemClickListener {

    public static PhotoPickerFragment newInstance() {
        Bundle args = new Bundle();
        PhotoPickerFragment fragment = new PhotoPickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //ui
    private int contentId = R.id.pickerContent;
    //fragment
    private PhotoPickFragment mPhotoPickFragment;
    private String mPickTag;
    //listener
    private OnPickerResultCallback onPickerResultCallback;

    @Override
    public int getRootViewId() {
        return R.layout.frg_photo_picker;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initPickFragment();
        showFragment(mPhotoPickFragment, contentId);
    }

    /**
     * 初始化图片选择器
     */
    private void initPickFragment() {
        if (TextUtils.isEmpty(mPickTag)) {
            mPhotoPickFragment = new PhotoPicker.Builder()
                    .maxPickSize(1)
                    .showCamera(false)
                    .imageLoader(new PhotoImageLoader())
                    .build().createPick();
            mPickTag = mPhotoPickFragment.getFragmentTAG();
        } else {
            mPhotoPickFragment = (PhotoPickFragment) SupportManager.getFragment(mChildFm, mPickTag);
        }
    }

    @Override
    public void initListener() {
        mPhotoPickFragment.addOnPhotoItemClickListener(this);
    }

    @Override
    public void onPhotoItemClick(int position, ArrayList<Photo> photos) {
        Photo photo = photos.get(position);
        String imagePath = ImageUtils.getImagePath(mContext, "/Crop/");
        File corpFile = new File(imagePath + ImageUtils.createFile());
        UCropUtils.start(this, mContext, new File(photo.getPath()), corpFile,
                false, R.color.colorPrimary);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        switch (requestCode) {
            case UCrop.REQUEST_CROP:
                handleClipResult(UCrop.getOutput(data));
                break;
            case UCrop.RESULT_ERROR:
                Throwable cropError = UCrop.getError(data);
                if (onPickerResultCallback != null && cropError != null) {
                    onPickerResultCallback.onPickFailed(cropError.getMessage());
                }
                break;
        }
    }

    /**
     * 处理裁剪结果
     */
    private void handleClipResult(Uri output) {
        String path = output.getPath();
        if (onPickerResultCallback != null) {
            onPickerResultCallback.onPickSuccess(path);
        }
    }

    /**
     * 设置图片选择器的结果监听器
     */
    public void setOnPickerResultCallback(OnPickerResultCallback onPickerResultCallback) {
        this.onPickerResultCallback = onPickerResultCallback;
    }

    /**
     * 图片选择的结果反馈接口
     */
    public interface OnPickerResultCallback {
        /**
         * 选择成功
         *
         * @param path 图片路径
         */
        void onPickSuccess(String path);

        /**
         * 选择失败
         *
         * @param error 失败信息
         */
        void onPickFailed(String error);
    }
}
