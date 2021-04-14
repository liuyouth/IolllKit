package com.iolll.kit.home.shuidi

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import java.util.*
import kotlin.collections.ArrayList

class CategoryPagerAdapter(
    fm:FragmentManager,
    var titleList : ArrayList<String>
) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return titleList.size
    }

    override fun getItem(position: Int): Fragment {
        return ChannelArticleFragment.newInstance(titleList[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }


}