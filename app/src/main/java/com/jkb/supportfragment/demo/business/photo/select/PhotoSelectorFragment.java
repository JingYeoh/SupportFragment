package com.jkb.supportfragment.demo.business.photo.select;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.base.ui.BaseFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.supportfragment.demo.R;
import com.jkb.supportfragment.demo.business.photo.picker.PhotoPickerFragment;

/**
 * 图片选择器
 * Created by yj on 2017/5/27.
 */
@Route(path = AppConfig.RouterPath.PHOTO_SELECRTOR)
public class PhotoSelectorFragment extends BaseFragment implements PhotoPickerFragment.OnPickerResultCallback, View
        .OnClickListener {

    @Override
    public int getRootViewId() {
        return R.layout.frg_photo_selector;
    }

    @Override
    public void initView() {
        ((TextView) findViewById(R.id.title_name)).setText(R.string.media);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            PhotoPickerFragment pickerFragment = PhotoPickerFragment.newInstance();
            pickerFragment.setOnPickerResultCallback(this);
            showFragment(pickerFragment, R.id.photoSelectorContent);
        }
    }

    @Override
    public void initListener() {
        findViewById(R.id.title_back).setOnClickListener(this);
    }

    @Override
    public void onPickSuccess(String path) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConfig.KeyBundle.PHOTO_PATH, path);
        setFragmentResult(RESULT_OK_FRAGMENT, bundle);
        closeCurrentAndShowPopFragment();
    }

    @Override
    public void onPickFailed(String error) {
        Bundle bundle = new Bundle();
        bundle.putString(AppConfig.KeyBundle.PHOTO_PATH, error);
        setFragmentResult(RESULT_CANCELED_FRAGMENT, bundle);
        closeCurrentAndShowPopFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                onPickFailed(null);
                break;
        }
    }
}
