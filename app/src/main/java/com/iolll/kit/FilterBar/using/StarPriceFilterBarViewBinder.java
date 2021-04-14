package com.iolll.kit.FilterBar.using;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.iolll.kit.FilterBar.FilterBar;
import com.iolll.kit.FilterBar.FilterBarViewBinder;
import com.iolll.kit.R;

/**
 * Created by LiuBo on 2019-07-04.
 */
public class StarPriceFilterBarViewBinder extends FilterBarViewBinder<StarPrice, StarPriceFilterBarViewBinder.ViewHolder> {



    @SuppressLint("InflateParams")
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater) {
        return new ViewHolder(inflater.inflate(R.layout.view_shaixuan, null, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, @NonNull StarPrice starPrice) {
        viewHolder.showTv.setText(starPrice.getName());
    }


    static class ViewHolder extends FilterBar.ViewHolder {
        @BindView(R.id.showTv)
        TextView showTv;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
