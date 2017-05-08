package com.jkb.supportfragment.demo.business.auth.areaCode;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.jkb.supportfragment.demo.R;
import com.jkb.supportfragment.demo.entity.auth.AreaCodeEntity;

import java.util.ArrayList;

/**
 * 地区编码的数据适配器
 * Created by yj on 2017/5/8.
 */

class AreaCodeAdapter extends RecyclerView.Adapter<AreaCodeAdapter.ViewHolder> {

    private Context context;
    public ArrayList<AreaCodeEntity> areaCode;
    private OnAreaCodeItemClickListener onAreaCodeItemClickListener;

    public AreaCodeAdapter(Context context, ArrayList<AreaCodeEntity> areaCode) {
        this.context = context;
        if (areaCode == null) areaCode = new ArrayList<>();
        this.areaCode = areaCode;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.item_area_code, parent, false);
        ViewHolder holder = new ViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        AreaCodeEntity entity = areaCode.get(position);
        holder.getBinding().setVariable(BR.areaCode, entity);
        holder.getBinding().executePendingBindings();
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAreaCodeItemClickListener == null) return;
                onAreaCodeItemClickListener.onAreaCodeItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return areaCode.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        ViewHolder(View itemView) {
            super(itemView);
        }

        private ViewDataBinding getBinding() {
            return binding;
        }

        void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }

    public interface OnAreaCodeItemClickListener {
        /**
         * 地区编码被点击
         */
        void onAreaCodeItemClick(int position);
    }

    public void setOnAreaCodeItemClickListener(OnAreaCodeItemClickListener onAreaCodeItemClickListener) {
        this.onAreaCodeItemClickListener = onAreaCodeItemClickListener;
    }
}
