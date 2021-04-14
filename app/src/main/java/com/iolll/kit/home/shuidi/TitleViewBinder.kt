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
open class TitleViewBinder(itemClick: ItemClick<Title, ViewHolder>) :
    BaseClickItemViewBinder<Title, TitleViewBinder.ViewHolder>(itemClick) {

    override fun onCreateViewHolder(
        inflater: LayoutInflater, parent: ViewGroup
    ): ViewHolder {
        val root = inflater.inflate(R.layout.item_default, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, string: Title) {
        super.onBindViewHolder(holder, string)
        holder.itemView.textTv.text = string.name
        holder.itemView.rv_header_group_layout.setBackgroundColor(Utils.getColor(if ("头像" == string.name) R.color.colorAccent else R.color.colorPrimary))
        if ("头像"==string.name){
            holder.itemView.topImg.visibility =View.VISIBLE
        }else {
            holder.itemView.topImg.visibility =View.GONE
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
