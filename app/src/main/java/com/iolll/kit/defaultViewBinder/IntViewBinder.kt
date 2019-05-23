package com.iolll.kit.defaultViewBinder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iolll.kit.R
import com.iolll.liubo.iolllviewx.RecyclerView.BaseClickItemViewBinder
import com.iolll.liubo.iolllviewx.RecyclerView.ItemClick
import kotlinx.android.synthetic.main.item_default.view.*

/**
 * Created by LiuBo on 2019-05-23.
 */
open class IntViewBinder(itemClick: ItemClick<Int, ViewHolder>) :
    BaseClickItemViewBinder<Int, IntViewBinder.ViewHolder>(itemClick) {

    override fun onCreateViewHolder(
        inflater: LayoutInflater, parent: ViewGroup
    ): ViewHolder {
        val root = inflater.inflate(R.layout.item_default, parent, false)
        return ViewHolder(root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, string: Int) {
        super.onBindViewHolder(holder, string)
        holder.itemView.textTv.text = string.toString()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
