package com.iolll.kit.home.shuidi

import android.content.Context
import androidx.viewpager.widget.ViewPager

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPagerUtils


/**
 * Created by LiuBo on 2021/4/14.
 */


class NestedScrollLLM : LinearLayoutManager {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    var recyclerView: RecyclerView? = null

    override fun getExtraLayoutSpace(state: RecyclerView.State?): Int {
        return 1980
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        if (dy > 0) {
            /**
             * 内容往上滑动，父 RV 优先滑动；父 RV 到底，子 RV 继续滑动。
             * 子 RV 若到底，则不滑动，保证父 RV 的越界动画。
             */
            var scrolledDy = super.scrollVerticallyBy(dy, recycler, state)
            if (dy != scrolledDy) {
                for (i in 0 until childCount) {
                    val nest = getChildAt(i)?.findVerticalScrollRecyclerView()
                    nest?.apply {
                        if (!isAtBottom()) {
                            scrollBy(0, dy - scrolledDy)
                            scrolledDy = dy
                        } else {
                            recyclerView?.stopScroll()
                        }
                    }
                }
            }
            return scrolledDy
        } else {
            /**
             * 内容往下滑动，子 RV 优先滑动；子 RV 到顶，父 RV 继续滑动。
             */

            for (i in 0 until childCount) {
                val nest = getChildAt(i)?.findVerticalScrollRecyclerView()
                nest?.apply {
                    println("!isAtTop"+!isAtTop() + " dy"+dy)
                    if (!isAtTop()) {
                        scrollBy(0, dy)
                        return dy
                    }
                }
            }
            return super.scrollVerticallyBy(dy, recycler, state)
        }
    }


}

private fun View.findVerticalScrollRecyclerView(): RecyclerView? {
    if (this !is ViewGroup) {
        if (this is RecyclerView) {
            return this
        } else {
            return null
        }
    } else {
        val nest = children.find { it.findVerticalScrollRecyclerView() != null }?.findVerticalScrollRecyclerView()
        println("nest $nest")
        if (nest != null) {
            return nest
        } else {
            if (isTargetNestScrollView()) {
                println("nest this $this")
                return this as RecyclerView
            } else {
                return null
            }
        }
    }
}

private fun View.isTargetNestScrollView(): Boolean {
    return (this is RecyclerView && this.isNestedScrollingEnabled && this.canScrollVertically(1) or this.canScrollVertically(-1))
}

private val ViewGroup.children: List<View>
    get() {

        val views = mutableListOf<View>()

        if (this is ViewPager) {
            ViewPagerUtils.getCurrentView(this)?.apply {
                views.add(this)
            }
            return views
        }

        for (i in 0 until childCount) {
            views.add(getChildAt(i))
        }
        return views
    }



/**
 * RecyclerView.canScrollVertically(1)的值表示是否能向上滚动，false表示已经滚动到底部
 * RecyclerView.canScrollVertically(-1)的值表示是否能向下滚动，false表示已经滚动到顶部
 */
fun RecyclerView.isAtBottom(): Boolean {
    return !canScrollVertically(1)
}

fun RecyclerView.isAtTop(): Boolean {
    return !canScrollVertically(-1)
}

