package com.iolll.kit.defaultViewBinder


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by LiuBo on 2019-11-13.
 */
public class KTRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
    override val containerView: View?
        get() = itemView
}