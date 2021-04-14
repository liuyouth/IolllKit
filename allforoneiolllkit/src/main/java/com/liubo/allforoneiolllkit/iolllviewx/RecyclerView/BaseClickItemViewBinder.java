package com.liubo.allforoneiolllkit.iolllviewx.RecyclerView;

/**
 * Created by LiuBo on 2018/12/18.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import me.drakeet.multitype.ItemViewBinder;


/**
 * 为ItemViewBinder添加多样化回调接口
 * Created by LiuBo on 2018/10/14.
 * UpDated by LiuBo on 2019年05月06日10:36:26 修改监听接口减少耦合
 */
public abstract class BaseClickItemViewBinder<T, VH extends RecyclerView.ViewHolder> extends ItemViewBinder<T, VH> {

    public Context context;
    public ItemClick<T, VH> itemClick;


    @NonNull
    @Override
    protected VH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        context = parent.getContext();
        return null;
    }

    @Override
    protected void onBindViewHolder(@NonNull VH vh, @NonNull T t) {
        if (itemClick!=null)itemClick.onClick(itemClick,t,getPosition(vh),vh,null);
    }

    public BaseClickItemViewBinder(ItemClick<T, VH> itemClick) {
        this.itemClick = itemClick;
    }

    public static abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
        }
    }
}

