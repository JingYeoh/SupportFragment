package com.jkb.supportfragment.demo.business.menu.right;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jkb.commonlib.helper.AppLauncher;
import com.jkb.supportfragment.demo.R;

/**
 * 右滑菜单适配器
 * Created by yangjing on 17-6-7.
 */

public class SlideRightMenuAdapter extends FragmentPagerAdapter {

    private int[] menuNames = new int[]{
            R.string.all_match, R.string.chat
    };

    private Context mContext;

    public SlideRightMenuAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return AppLauncher.launchUserFriendsList();
        } else {
            return AppLauncher.launchUserChatList();
        }
    }

    @Override
    public int getCount() {
        return menuNames.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(menuNames[position]);
    }
}
