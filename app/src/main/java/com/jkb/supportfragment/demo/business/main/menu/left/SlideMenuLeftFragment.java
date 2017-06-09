package com.jkb.supportfragment.demo.business.main.menu.left;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.base.ui.BaseFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.ui.annotation.SupportContent;
import com.jkb.commonlib.ui.annotation.SupportWindow;
import com.jkb.supportfragment.demo.R;

/**
 * 左侧滑菜单
 * Created by yangjing on 17-6-5.
 */
@Route(path = AppConfig.RouterPath.APP_MAIN_SLIDE_LEFT)
@SupportWindow(immersiveStatus = true)
@SupportContent(contentViewId = R.layout.frg_menu_left)
public class SlideMenuLeftFragment extends BaseFragment {

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
