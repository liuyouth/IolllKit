package com.iolll.kit.FilterBar;

import android.view.LayoutInflater;
import androidx.annotation.NonNull;

/**
 * Created by LiuBo on 2019-07-04.
 */
public abstract class FilterBarViewBinder <T,VH extends FilterBar.ViewHolder>{
    public VH vh;
    public T t;
    protected abstract VH onCreateViewHolder(@NonNull LayoutInflater inflater);

    protected abstract void onBindViewHolder(@NonNull VH vh,@NonNull T t) ;
}
