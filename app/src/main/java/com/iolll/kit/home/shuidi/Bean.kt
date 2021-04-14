package com.iolll.kit.home.shuidi

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.iolll.kit.R
import com.iolll.kit.defaultViewBinder.KTRecyclerViewViewHolder
import com.iolll.kit.home.shuidi.ShuidiMainActivity.Companion.currentPosition
import com.iolll.liubo.ifunction.IFunction
import com.iolll.liubo.iolllviewx.RecyclerView.BaseClickItemViewBinder
import com.iolll.liubo.iolllviewx.RecyclerView.ItemClick
import kotlinx.android.synthetic.main.activity_shuidi_main.*
import kotlinx.android.synthetic.main.item_tab_suck_top.view.*

open class Title(
    var name:String,
    var isTop:Boolean ,// 是否可以在顶部滞留
    var isHeader:Boolean // 是否可以在Header滞留



) {
    override fun toString(): String {
        return "Title(name='$name', isTop=$isTop, isHeader=$isHeader)"
    }
}

class TitleChildren(
    var name:String
)

class TabSuckTop(var titles: ArrayList<String>, name: String, isTop: Boolean, isHeader: Boolean)
    :Title(name, isTop, isHeader)
open class TabSuckTopViewBinder(var vpSetupCallBack: IFunction.RunR<ViewPager,FragmentManager>,itemClick: ItemClick<TabSuckTop, KTRecyclerViewViewHolder>) :
    BaseClickItemViewBinder<TabSuckTop, KTRecyclerViewViewHolder>(itemClick) {

    override fun onCreateViewHolder(
        inflater: LayoutInflater, parent: ViewGroup
    ): KTRecyclerViewViewHolder {
        val root = inflater.inflate(R.layout.item_tab_suck_top, parent, false)
        return KTRecyclerViewViewHolder(root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: KTRecyclerViewViewHolder, data: TabSuckTop) {
        super.onBindViewHolder(holder, data)
        holder.itemView.tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {

            }
        })
        for (i in 0 until holder.itemView.tabLayout.tabCount) {
            val tab: TabLayout.Tab? = holder.itemView.tabLayout.getTabAt(i)
            tab?.customView = getTabView(data.titles[i], i, holder.itemView.tabLayout.tabCount,holder.itemView.context)
        }
        println("tabLayout + "+holder.itemView.tabLayout)
        holder.itemView.tabLayout.setupWithViewPager(holder.itemView.viewPager)
        /**
         * 将vp暴漏出去 并拿回所需的FM fragmentManager
         */
        holder.itemView.viewPager.adapter = CategoryPagerAdapter(vpSetupCallBack.run(holder.itemView.viewPager),data.titles)
    }

companion object{

    fun getTabView(title: String, curPos: Int, count: Any, context: Context): View {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.tab_textview, null)
        val textView = view.findViewById<TextView>(R.id.tab_item_textview)
        textView.text = title
        if (count == 1) {
            textView.textSize = 16f
            textView.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
            textView.setTextColor(ContextCompat.getColor(context, R.color.textBlack))
        } else {
            if (curPos == currentPosition) {
                textView.textSize = 16f
                textView.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                textView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.colorAccent
                    )
                )
            } else {
                textView.textSize = 16f
                textView.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
                textView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.textBlack
                    )
                )
            }
        }
        return view
    }

}




















}