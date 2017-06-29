package com.jkb.supportfragment.demo.business.auth.areaCode;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jkb.commonlib.base.ui.BaseDialogFragment;
import com.jkb.commonlib.config.AppConfig;
import com.jkb.supportfragment.demo.R;
import com.jkb.supportfragment.demo.databinding.FrgAuthAreaCodeBinding;
import com.jkb.supportfragment.demo.entity.auth.AreaCodeEntity;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;

/**
 * 地区编号
 * Created by yj on 2017/5/8.
 */
@Route(path = AppConfig.RouterPath.AUTH_AREA_CODE)
public class AreaCodeDialogFragment extends BaseDialogFragment<FrgAuthAreaCodeBinding> implements AreaCodeAdapter
        .OnAreaCodeItemClickListener {

    private ArrayList<AreaCodeEntity> areaCode;
    private RecyclerView rvAreaCode;
    private AreaCodeAdapter areaCodeAdapter;

    @Override
    public int getRootViewId() {
        return R.layout.frg_auth_area_code;
    }

    @Override
    public void initView() {
        rvAreaCode = (RecyclerView) findViewById(R.id.areaCode_rv);
        rvAreaCode.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        areaCode = new ArrayList<>();
        Bundle args = savedInstanceState;
        if (savedInstanceState == null) {
            args = getArguments();
        }
        areaCode = (ArrayList<AreaCodeEntity>) args.getSerializable(AppConfig.KeyBundle.AREACODE);

        areaCodeAdapter = new AreaCodeAdapter(mContext, areaCode);
        rvAreaCode.setAdapter(areaCodeAdapter);
    }

    @Override
    public void initListener() {
        areaCodeAdapter.setOnAreaCodeItemClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(AppConfig.KeyBundle.AREACODE, areaCode);
    }

    @Override
    public boolean isSupportMVVM() {
        return true;
    }

    @Override
    public void onAreaCodeItemClick(int position) {
        //发送通知
        Message message = Message.obtain();
        message.obj = areaCode.get(position);
        EventBus.getDefault().post(message, AppConfig.EventBusTAG.AUTH_AREA_CODE_SELECTE);
        dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
