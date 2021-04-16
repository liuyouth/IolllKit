package com.iolll.kit.home.shuidi

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.iolll.kit.R
import com.iolll.kit.defaultViewBinder.IntViewBinder
import com.iolll.kit.defaultViewBinder.IntegerViewBinder
import com.iolll.kit.defaultViewBinder.StringViewBinder
import com.iolll.kit.home.shuidi.TabSuckTopViewBinder.Companion.getTabView
import com.iolll.liubo.ifunction.IFunction
import com.iolll.liubo.iolllviewx.RecyclerView.ItemClick
import com.iolll.liubo.iolllviewx.RecyclerView.LinearLayoutManagerX
import com.safframework.log.L
import kotlinx.android.synthetic.main.activity_on_top_rv.*
import kotlinx.android.synthetic.main.activity_on_top_rv.recyclerView
import kotlinx.android.synthetic.main.activity_on_top_rv.rv_header_group_layout
import kotlinx.android.synthetic.main.activity_on_top_rv.toolbarLayout
import kotlinx.android.synthetic.main.activity_on_top_rv.view.*
import kotlinx.android.synthetic.main.activity_shuidi_main.*
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

@Route(path = "/home/shuidi")
class ShuidiMainActivity : AppCompatActivity() {
    val items = Items()
    val adapter = MultiTypeAdapter()
    companion object{
        /**
         * VP 当前选中
         */

          var currentPosition = 0
    }

    private lateinit var channelsEntityList : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        L.init(this::class.java)
        setContentView(R.layout.activity_shuidi_main)

        items.add(Title("头像", true, true))
        items.add(TitleChildren("子类"))
        items.add(TitleChildren("子类"))
        channelsEntityList =arrayListOf("推荐","健康科普","养生")
        items.add(TabSuckTop(channelsEntityList,"", isTop = false, isHeader = true))
//        items.add(Title("2019-10-32", false, true))
//        items.add(TitleChildren("子类"))
//        items.add(TitleChildren("子类"))
//        items.add(TitleChildren("子类"))
//        items.add(Title("2019-10-33", false, true))
//        items.add(TitleChildren("子类"))
//        items.add(TitleChildren("子类"))
//        items.add(TitleChildren("子类"))
//        items.add(Title("相关推荐", true, true))
//        items.add(TitleChildren("子类"))
//        items.add(TitleChildren("子类"))
//        items.add(TitleChildren("子类"))


//        channelsEntityList.addAll(arrayListOf("推荐"))
        adapter.register(
            Title::class.java,
            object : TitleViewBinder(ItemClick { self, t, position, viewHolder, view ->

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
        adapter.register(TabSuckTop::class.java,object :
            TabSuckTopViewBinder(IFunction.RunR { tabLayout.setupWithViewPager(it); supportFragmentManager },
                ItemClick { self, t, position, viewHolder, view ->


        }){})
//        tabLayout.setSelectedTabIndicator(R.drawable.ic_launcher_foreground)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {

            }

        })
        for (i in 0 until tabLayout.tabCount) {
            val tab: TabLayout.Tab? = tabLayout.getTabAt(i)
            tab?.customView = getTabView(channelsEntityList[i], i, tabLayout.tabCount,this)
        }
        adapter.items = items
//        recyclerView.layoutManager = LinearLayoutManagerX(this)
        recyclerView.layoutManager = NestedScrollLLM(this)
        recyclerView.addItemDecoration(
            RVDis2(
                items,
                toolbarLayout,
                rv_header_group_layout,
                recyclerView.layoutManager as NestedScrollLLM,
                getViBean(), object : TopBarCallBack {
                    override fun callback(view: View, x: Float, title: TabSuckTop) {
//                        val textView = view.onTopHeaderTv
//                        textView.translationX = x
                    }
                }, object : RVDis2.HeaderCallBack<Title> {

                    override fun onHeaderCallBack(t: Title) {
//                        onTopHeaderTv.text = "喂喂喂无"
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

    /**
     * 查找是否是可以置顶的类型
     */
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
                    } while (if (getTypeString(position)) false else position - 1 >= 0 && !getTypeString(
                            position--
                        )
                    )
                }
                println("VirtualFamily ${position}   ccc $childPosition")
                return position
            }
        }

    }
}