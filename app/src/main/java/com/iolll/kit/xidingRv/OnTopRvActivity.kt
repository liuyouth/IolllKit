package com.iolll.kit.xidingRv


import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.iolll.ki.RVDis2
import com.iolll.kit.BaseActivity
import com.iolll.kit.R
import com.iolll.kit.defaultViewBinder.IntViewBinder
import com.iolll.kit.defaultViewBinder.IntegerViewBinder
import com.iolll.kit.defaultViewBinder.StringViewBinder
import com.iolll.liubo.iolllviewx.RecyclerView.ItemClick
import com.iolll.liubo.iolllviewx.RecyclerView.LinearLayoutManagerX
import com.safframework.log.L
import kotlinx.android.synthetic.main.activity_on_top_rv.*
import kotlinx.android.synthetic.main.activity_on_top_rv.view.*
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

@Route(path = "/recyclerview/ontop")
class OnTopRvActivity : BaseActivity() {
    val items = Items()
    val adapter = MultiTypeAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        L.init(this::class.java)
        setContentView(R.layout.activity_on_top_rv)
//        items.add("20123131")
//        items.add(123)
//        items.add(123)
//        items.add(123)
//        items.add(123)
//        items.add(123)
//        items.add(123)
//        items.add(123)
//        items.add("201231313333333")
//        items.add(12333)
//        items.add(123)
//        items.add(123)
//        items.add(123)
//        items.add(123)
//        items.add(12333)
//        items.add(1233333)
//        items.add("20123131")
//        items.add(12333)
//        items.add("eeeee")
//        items.add("eeeee")
        items.add(Title("头像", true, true))
        items.add(TitleChildren("子类"))
        items.add(Title("2019-10-32", false, true))
        items.add(TitleChildren("子类"))
        items.add(TitleChildren("子类"))
        items.add(TitleChildren("子类"))
        items.add(Title("2019-10-33", false, true))
        items.add(TitleChildren("子类"))
        items.add(TitleChildren("子类"))
        items.add(TitleChildren("子类"))
        items.add(Title("相关推荐", true, true))
        items.add(TitleChildren("子类"))
        items.add(TitleChildren("子类"))
        items.add(TitleChildren("子类"))


        adapter.register(Title::class.java, object : TitleViewBinder(ItemClick { self, t, position, viewHolder, view ->

        }) {})
        adapter.register(
            TitleChildren::class.java,
            object : TitleChildrenViewBinder(ItemClick { self, t, position, viewHolder, view ->


            }) {})
        adapter.register(String::class.java,
            object : StringViewBinder(ItemClick { self, t, position, viewHolder, view -> }) {})
        adapter.register(Int::class.java,
            object : IntViewBinder(ItemClick { self, t, position, viewHolder, view -> }) {})
        adapter.register(Integer::class.java,
            object : IntegerViewBinder(ItemClick { self, t, position, viewHolder, view -> }) {})

        adapter.items = items
        recyclerView.layoutManager = LinearLayoutManagerX(this)
        recyclerView.addItemDecoration(
            RVDis2(
                items,
                toolbarLayout,
                rv_header_group_layout,
                onTopHeaderTv,
                recyclerView.layoutManager as LinearLayoutManagerX,
                getViBean(),object :TopBarCallBack{
                    override fun callback(view: View, x: Float, title: Title) {
                        val textView = view.onTopHeaderTv
                        textView.translationX = x
                    }
                }, object : RVDis2.HeaderCallBack<Title> {
                    override fun onHeaderCallBack(t: Title) {
                        onTopHeaderTv.text = t.name
                    }
                })
        )
        recyclerView.adapter = adapter

    }

    fun getType(index: Int): Boolean {
        return items[index] is Title
    }

    fun getTypeString(index: Int): Boolean {
        return items[index] is String
    }

    fun getViBean(): VirtualFamily {
        return object : VirtualFamily {
            override fun isParentType(position: Int): Boolean {
                if (position < 0 || position >= items.size) {
                    return false
                }
                return items[position] is Title
            }

            override fun parentChildren(position: Int): Int {
                var viewType: Int
                var number = -1
                var pos = position
                do {
//                            getType(pos++)
                    number++
                } while (items[pos++] !is Title)
                return number
            }

            override fun isChildType(position: Int): Boolean {
                if (position < 0 || position >= items.size) {
                    return false
                }
                return items[position] !is Title
            }

            override fun childParentPosition(childPosition: Int): Int {
                var re = -1
                for (i in childPosition downTo 0) {
//                    println(" i $i chi $childPosition")
                    if (items[i] is Title) {
                        if ((items[i] as Title).isHeader) {
                            re = i
                            break
                        }
                    }
                }
                return re


//                var position = childPosition
//                if (childPosition > 0) {
//                    do {
////                                viewType = getType(--position)
//                    } while (if (getType(position)) false else position - 1 >= 0 && !getType(position--))
//                }
//                return position

            }
        }
    }

    fun getViString(): VirtualFamily {
        return object : VirtualFamily {
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
                    } while (if (getTypeString(position)) false else position - 1 >= 0 && !getTypeString(position--))
                }
                println("VirtualFamily ${position}   ccc $childPosition")
                return position
            }
        }
    }

}
