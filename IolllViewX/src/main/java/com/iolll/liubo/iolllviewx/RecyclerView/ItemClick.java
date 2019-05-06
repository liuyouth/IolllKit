package com.iolll.liubo.iolllviewx.RecyclerView;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by LiuBo on 2019-05-05.
 */

public interface ItemClick<T, VH extends RecyclerView.ViewHolder> {
    void onClick(ItemClick<T, VH> self, T t, int position, VH viewHolder, View view);
}

