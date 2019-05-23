package com.iolll.kit.xidingRv


import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.iolll.kit.BaseActivity
import com.iolll.kit.R
import com.iolll.kit.defaultViewBinder.IntViewBinder
import com.iolll.kit.defaultViewBinder.IntegerViewBinder
import com.iolll.kit.defaultViewBinder.StringViewBinder
import com.iolll.liubo.iolllviewx.RecyclerView.ItemClick
import com.iolll.liubo.iolllviewx.RecyclerView.LinearLayoutManagerX
import kotlinx.android.synthetic.main.activity_on_top_rv.*
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter
@Route(path = "/recyclerview/ontop")
class OnTopRvActivity : BaseActivity() {
    val items = Items()
    val adapter = MultiTypeAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_top_rv)
        items.add("20123131")
        items.add(123)
        items.add(123)
        items.add(123)
        items.add(123)
        items.add(123)
        items.add(123)
        items.add(123)
        items.add("201231313333333")
        items.add(12333)
        items.add(123)
        items.add(123)
        items.add(123)
        items.add(123)
        items.add(12333)
        items.add(1233333)
        items.add("20123131")
        items.add(12333)

        adapter.register(String::class.java,
            object : StringViewBinder(ItemClick { self, t, position, viewHolder, view -> }) {})
        adapter.register(Int::class.java,
            object : IntViewBinder(ItemClick { self, t, position, viewHolder, view -> }) {})
        adapter.register(Integer::class.java,
            object : IntegerViewBinder(ItemClick { self, t, position, viewHolder, view -> }) {})

        adapter.items = items
        recyclerView.layoutManager = LinearLayoutManagerX(this)
        recyclerView.addItemDecoration(
            RVDis(
                rv_header_group_layout,
                onTopHeaderTv,
                recyclerView.layoutManager as LinearLayoutManagerX,
                object : VirtualFamily {
                    override fun isParentType(position: Int): Boolean {
                        if (position < 0 || position >= items.size) {
                            return false
                        }
                        return items[position] is String
                    }

                    override fun parentChildren(position: Int): Int {
                        var viewType: Int
                        var number = -1
                        var pos = position
                        do {
//                            getType(pos++)
                            number++
                        } while (items[pos++] !is String)
                        return number
                    }

                    override fun isChildType(position: Int): Boolean {
                        if (position < 0 || position >= items.size) {
                            return false
                        }
                        return items[position] !is String
                    }

                    override fun childParentPosition(childPosition: Int): Int {
//                        var viewType: Int
                        var position = childPosition
                        if (childPosition > 0) {
                            do {
//                                viewType = getType(--position)
                            } while (if (getType(position)) false else position - 1 >= 0 && !getType(position--))
                        }
                        println("VirtualFamily ${position}   ccc $childPosition")
                        return position
                    }
                })
        )
        recyclerView.adapter = adapter

    }
    fun getType(index: Int): Boolean {
        return items[index] is String
    }

}
