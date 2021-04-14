package com.iolll.kit.TagView

import android.view.LayoutInflater
import android.view.ViewGroup
import com.iolll.kit.R
import com.iolll.kit.defaultViewBinder.KTRecyclerViewViewHolder
import com.liubo.allforoneiolllkit.iolllviewx.RecyclerView.BaseClickItemViewBinder
import com.liubo.allforoneiolllkit.iolllviewx.RecyclerView.ItemClick
import kotlinx.android.synthetic.main.item_tag.view.*

/**
 * Created by LiuBo on 2021-04-11.
 */
class TagCell(var name: String, var toList: List<String>)
class TagCellViewBinder(clickListener: ItemClick<TagCell,KTRecyclerViewViewHolder>) : BaseClickItemViewBinder<TagCell, KTRecyclerViewViewHolder>(clickListener) {
    override fun onCreateViewHolder(
        inflater: LayoutInflater, parent: ViewGroup
    ): KTRecyclerViewViewHolder {
        val root = inflater.inflate(R.layout.item_tag, parent, false)
        return KTRecyclerViewViewHolder(root)
    }

    override fun onBindViewHolder(holder: KTRecyclerViewViewHolder, item: TagCell) {
        holder.itemView.tv.text = item.name
        holder.itemView.tagGrsoup.setData(item.toList)
        holder.itemView.setOnClickListener{
            itemClick.onClick(itemClick,item,getPosition(holder),holder,holder.itemView)
        }
    }
}