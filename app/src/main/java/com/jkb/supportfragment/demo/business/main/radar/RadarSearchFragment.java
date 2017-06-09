package com.jkb.supportfragment.demo.business.main.radar;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.app.AppManager;
import com.jkb.commonlib.base.ui.BaseFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.db.entity.User;
import com.jkb.commonlib.exception.auth.AuthException;
import com.jkb.commonlib.ui.annotation.SupportContent;
import com.jkb.commonlib.ui.annotation.SupportWindow;
import com.jkb.supportfragment.demo.R;
import com.jkb.supportfragment.demo.databinding.FrgRadarBinding;
import com.jkb.supportfragment.demo.entity.main.RadarEntity;

/**
 * 雷达搜索
 * Created by yangjing on 17-6-9.
 */
@Route(path = AppConfig.RouterPath.APP_MAIN_RADAR)
@SupportWindow(immersiveStatus = true)
@SupportContent(contentViewId = R.layout.frg_radar)
public class RadarSearchFragment extends BaseFragment<FrgRadarBinding> {

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        RadarEntity radarEntity = new RadarEntity();
        getBinding().setRadar(new RadarEntity());
        try {
            User user = AppManager.getInstance().getCurrentUser();
            radarEntity.setAvatar(user.getAvatar());
        } catch (AuthException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public boolean isSupportMVVM() {
        return true;
    }
}
