package com.jkb.supportfragment.demo.business.user.chat;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.base.ui.BaseFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.commonlib.ui.annotation.SupportContent;
import com.jkb.commonlib.ui.annotation.SupportWindow;
import com.jkb.supportfragment.demo.R;

/**
 * 好友列表
 * Created by yangjing on 17-6-7.
 */
@Route(path = AppConfig.RouterPath.USER_CHAT_LIST)
@SupportWindow(immersiveStatus = true)
@SupportContent(contentViewId = R.layout.frg_user_chat_list)
public class ChatListFragment extends BaseFragment {

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
