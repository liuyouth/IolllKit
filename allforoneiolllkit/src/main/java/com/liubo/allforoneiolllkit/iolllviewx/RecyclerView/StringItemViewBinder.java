package com.liubo.allforoneiolllkit.iolllviewx.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by LiuBo on 2019-05-05.
 */
public class StringItemViewBinder extends BaseClickItemViewBinder<String, StringItemViewBinder.ViewHolder> {


    public StringItemViewBinder(ItemClick<String, ViewHolder> itemClick) {
        super(itemClick);
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
//        View root = inflater.inflate(R.layout.item_text_item, parent, false);
        View root = new LinearLayout(parent.getContext());
        TextView textView = new TextView(root.getContext());
        textView.setTag("eee");
        textView.setPadding(20,20,20,20);
        textView.setTextSize(20);
        ((LinearLayout) root).addView(textView);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull String textItem) {
        super.onBindViewHolder(holder, textItem);
        setData(holder, textItem);
    }

    public static void setData(ViewHolder holder, String textItem) {
        holder.infoTv.setText(textItem);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //        @BindView(R2.id.nameTv)
        public TextView infoTv;
        ViewHolder(View itemView) {
            super(itemView);
            infoTv = itemView.findViewWithTag("eee");
//            ButterKnife.bind(this,itemView);
        }
    }
}
