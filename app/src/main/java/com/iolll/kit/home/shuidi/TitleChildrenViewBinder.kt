package com.iolll.kit.home.shuidi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iolll.kit.R
import com.iolll.kit.Utils
import com.iolll.liubo.iolllviewx.RecyclerView.BaseClickItemViewBinder
import com.iolll.liubo.iolllviewx.RecyclerView.ItemClick
import kotlinx.android.synthetic.main.item_default.view.*

/**
 * Created by LiuBo on 2019-05-23.
 */
open class TitleChildrenViewBinder(itemClick: ItemClick<TitleChildren, ViewHolder>) :
    BaseClickItemViewBinder<TitleChildren, TitleChildrenViewBinder.ViewHolder>(itemClick) {

    override fun onCreateViewHolder(
        inflater: LayoutInflater, parent: ViewGroup
    ): ViewHolder {
        val root = inflater.inflate(R.layout.item_default, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, string: TitleChildren) {
        super.onBindViewHolder(holder, string)
        holder.itemView.textTv.text = string.name
        holder.itemView.rv_header_group_layout.setBackgroundColor(Utils.getColor(R.color.colorPrimary))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)



}
