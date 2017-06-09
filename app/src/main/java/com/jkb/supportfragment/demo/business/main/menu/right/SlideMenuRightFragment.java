package com.jkb.supportfragment.demo.business.main.menu.right;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.base.ui.BaseFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.ui.annotation.SupportContent;
import com.jkb.commonlib.ui.annotation.SupportWindow;
import com.jkb.supportfragment.demo.R;

/**
 * 右侧滑菜单
 * Created by yangjing on 17-6-5.
 */
@Route(path = AppConfig.RouterPath.APP_MAIN_SLIDE_RIGHT)
@SupportWindow(immersiveStatus = true)
@SupportContent(contentViewId = R.layout.frg_menu_right)
public class SlideMenuRightFragment extends BaseFragment {

    //ui
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //data
    private SlideRightMenuAdapter slideRightMenuAdapter;

    @Override
    public void initView() {
        tabLayout = (TabLayout) findViewById(R.id.fmr_tab);
        viewPager = (ViewPager) findViewById(R.id.fmr_vp);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        if (slideRightMenuAdapter == null) {
            slideRightMenuAdapter = new SlideRightMenuAdapter(mContext, mChildFm);
        }
        viewPager.setAdapter(slideRightMenuAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initListener() {

    }

}
