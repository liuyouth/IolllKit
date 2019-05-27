package com.iolll.kit;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.iolll.kit.router.RouteAll;
import com.iolll.liubo.iolllviewx.RecyclerView.BaseClickItemViewBinder;
import com.iolll.liubo.iolllviewx.RecyclerView.ItemClick;

/**
 * Created by LiuBo on 2019-05-09.
 */
public class RouteAllViewBinder extends BaseClickItemViewBinder<RouteAll, RouteAllViewBinder.ViewHolder> {



    public RouteAllViewBinder(ItemClick<RouteAll, ViewHolder> itemClick) {
        super(itemClick);
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_page_info_layout, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull RouteAll routeAll) {
        super.onBindViewHolder(holder,routeAll);
        holder.textName.setText(routeAll.getName());
        holder.infoTv.setText(routeAll.getUri());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textName)
        TextView textName;
        @BindView(R.id.infoTv)
        TextView infoTv;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
