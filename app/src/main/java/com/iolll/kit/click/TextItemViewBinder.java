package com.iolll.kit.click;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.iolll.kit.R;
import com.iolll.liubo.iolllviewx.RecyclerView.BaseClickItemViewBinder;
import com.iolll.liubo.iolllviewx.RecyclerView.ItemClick;

/**
 * Created by LiuBo on 2019-05-05.
 */
public class TextItemViewBinder extends BaseClickItemViewBinder<TextItem, TextItemViewBinder.ViewHolder> {


    public TextItemViewBinder(ItemClick<TextItem, ViewHolder> itemClick) {
        super(itemClick);
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_text_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull TextItem textItem) {
        super.onBindViewHolder(holder, textItem);
        setData(holder,textItem);
    }

    public static void setData(ViewHolder holder, TextItem textItem) {
        holder.infoTv.setText(textItem.getInfo());
        holder.textName.setText(textItem.getName());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textName)
        public TextView textName;
        @BindView(R.id.infoTv)
        public TextView infoTv;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
