package com.iolll.kit.click

import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.ButterKnife
import com.iolll.kit.BaseActivity
import com.iolll.kit.R
import com.iolll.liubo.ifunction.IFunction
import com.iolll.liubo.iolllfunction.ClickListener
import com.iolll.liubo.niceutil.NiceUtil
import kotlinx.android.synthetic.main.activity_main.*
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

class ClickKotlinActivity : BaseActivity() {


    private val adapter = MultiTypeAdapter()
    private val items = Items()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
        items.add(TextItem())
        items.add(TextItem())
        items.add(TextItem())
        items.add(TextItem())
        items.add(TextItem())
        items.add(TextItem())
        items.add(TextItem())
        items.add(TextItem())
        items.add(TextItem())
        items.add(TextItem())
        items.add(TextItem())
        items.add(TextItem())
        items.add(TextItem())
        items.add(TextItem())
        items.add(TextItem())
        adapter.items = items
        adapter.register(TextItem::class.java, TextItemViewBinder { self, textItem, position, viewHolder, view ->
            if (view != null) {
                when (view.id) {
                    viewHolder.textName.id -> textItem.name = "iiiiiinin"
                    viewHolder.infoTv.id -> textItem.info = "卫计委"
                }
                TextItemViewBinder.setData(viewHolder, textItem)
            } else {
                NiceUtil.forEach<TextView>(IFunction.Run<TextView> {
                    it.setOnClickListener(ClickListener { v -> self.onClick(self, textItem, position, viewHolder, v) })
                }, viewHolder.textName, viewHolder.infoTv)
            }
        })
    }
}
