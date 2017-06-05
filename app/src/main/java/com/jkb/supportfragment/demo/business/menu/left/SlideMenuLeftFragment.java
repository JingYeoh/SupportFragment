package com.jkb.supportfragment.demo.business.menu.left;

import android.os.Bundle;

import com.jkb.commonlib.base.ui.BaseFragment;
import com.jkb.commonlib.ui.annotation.SupportContent;
import com.jkb.commonlib.ui.annotation.SupportWindow;
import com.jkb.supportfragment.demo.R;

/**
 * 左侧滑菜单
 * Created by yangjing on 17-6-5.
 */
@SupportWindow(immersiveStatus = true)
@SupportContent(contentViewId = R.layout.frg_menu_left)
public class SlideMenuLeftFragment extends BaseFragment {

    public static SlideMenuLeftFragment newInstance() {
        Bundle args = new Bundle();
        SlideMenuLeftFragment fragment = new SlideMenuLeftFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initListener() {

    }

}
