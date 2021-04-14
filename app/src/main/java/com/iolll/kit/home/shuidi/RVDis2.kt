package com.iolll.kit.home.shuidi

import android.graphics.Canvas
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iolll.kit.Utils


import com.iolll.liubo.bottomrecyclerdialog.DensityUtils
import com.safframework.log.L


/**
 * 因为是提前让view 置顶 所以采用的是 判断屏幕上第二个view 的顶部距离
 * Created by LiuBo on 2019-05-23.
 */
class RVDis2(
    val items: List<Any>,
    private val toolbar: LinearLayout,
    private val layout: LinearLayout,

    private var layoutManage: LinearLayoutManager,
    private var virtualFamily: VirtualFamily,
    private var onTopBarCallBack: TopBarCallBack,
    private val callBack: HeaderCallBack<Title>
) : RecyclerView.ItemDecoration() {
    private var headerPosition: Int = -1
    var toTop: Int = 0
    val headerHeight = DensityUtils.dp2px(Utils.getContext(), 50f)
    val headerBottomY = DensityUtils.dp2px(Utils.getContext(), 100f)
    val toolbarBottomY = DensityUtils.dp2px(Utils.getContext(), 50f)
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val firstPos = layoutManage.findFirstVisibleItemPosition()
        val secondPos = layoutManage.findFirstVisibleItemPosition() + 1
        val endPos = layoutManage.findLastVisibleItemPosition()
        val fristHeaderPos = virtualFamily.childParentPosition(firstPos)
        val secondHeaderPos = virtualFamily.childParentPosition(secondPos)
        println("endPos $endPos header $fristHeaderPos SePos$secondPos  header $secondHeaderPos")
        if (firstPos == 0) {
            val view = layoutManage.findViewByPosition(firstPos)
            if (null != view) {
                toTop = -view.top
                layout.visibility = View.INVISIBLE
//                L.e("第一个 toTop$toTop  toolbarBottomY$toolbarBottomY")
                toolbar.alpha = toTop/580f
//                println("alpha "+toTop/580f)

//                if (toTop <= (headerBottomY * 3)) {
//                    toolbar.alpha = 0f
////                    toolbar.translationY = -toolbarBottomY.toFloat()
//                    toolbar.visibility = View.GONE
//                } else {
//                    toolbar.visibility = View.VISIBLE
//                    toolbar.alpha = 1f
//                }
            }
        } else {
            if (endPos < secondPos) {
                // 即 该页面只有一个view 的情况

            } else {
                // 目前方法只有置顶类型处于第二view 才会进去流程
                println(" se$secondPos  isHeader${isHeader(items[secondPos])} ")
                if (isHeader(items[secondPos])) {
                    val view = layoutManage.findViewByPosition(secondPos)
                    with(view) {
                        if (this != null) {
                            toTop = top
//                        val transY = (-headerBottomY - toTop).toFloat()
                            println("headerBottomY ${headerBottomY}  toolbarBottomY ${toolbarBottomY}  top$top height$height   toTop ${toTop}")

                            if (toTop <= headerBottomY) {
                                if (toTop <= toolbarBottomY) {
                                    secondTitleTouchedToolbar(
                                        view!!,
                                        getTitle(secondHeaderPos), toTop.toFloat(),
                                        if (fristHeaderPos != -1) (items[fristHeaderPos] as Title) else null,
                                        layout, toolbar, callBack
                                    )
                                } else {
                                    secondTitleTouchedHeader(
                                        items[secondPos] as Title, toTop.toFloat(),
                                        if (fristHeaderPos != -1) (items[fristHeaderPos] as Title) else null,
                                        layout, toolbar, callBack
                                    )
                                }
                            } else {
                                // 处理第二个view 没有 滑动到 置顶区域的情况
                                // 获取第一个view 的 置顶类型 进行显示
                                val firstTitlePos = virtualFamily.childParentPosition(firstPos)
                                if (-1 != fristHeaderPos) {
                                    if (isHeader(items[firstTitlePos])) {
                                        secondTitleUntouchedHeader(
                                            items[firstTitlePos] as Title,
                                            toTop.toFloat(),
                                            items[virtualFamily.childParentPosition(firstTitlePos)] as Title,
                                            layout,
                                            toolbar,
                                            callBack
                                        )
                                    } else {
                                        layout.visibility = View.INVISIBLE
//                                        toolbar.visibility = View.INVISIBLE
                                    }
                                } else {
                                    layout.visibility = View.INVISIBLE
//                                    toolbar.visibility = View.INVISIBLE
                                }
                            }
                        }
                        // view == null 的情况不会出现  在上面判断了边界
                    }
                } else {

                    if (-1 != fristHeaderPos)
                        if (isHeader(items[fristHeaderPos])) {
                            L.e("fristHeaderPos $fristHeaderPos  isHeader ${isHeader(items[fristHeaderPos])} firstPos $firstPos ")
                            val view = layoutManage.findViewByPosition(firstPos)
                            if (null != view) {
                                val ti = getTitle(fristHeaderPos)
                                if (null != ti) {
                                    if (ti.isHeader) {
//                                view.top.toFloat()-headerHeight
                                        if (ti.isTop) {
//                                            toolbar.translationY = 0f
//                                            toolbar.visibility = View.VISIBLE
                                            // 投递数据
                                            //修复位置错位
                                            layout.translationY = (-headerHeight).toFloat()
                                            layout.visibility = View.VISIBLE
                                        } else {
                                            layout.translationY = 0f
                                            layout.visibility = View.VISIBLE
                                        }
                                    }

                                }

                            }

                        } else {
                            layout.visibility = View.INVISIBLE
//                            toolbar.visibility = View.INVISIBLE
                        }
//                layout.visibility = View.INVISIBLE
//                toolbar.visibility = View.INVISIBLE
                    // 非置顶类型处于第二位置 并且在 限定范围外
                }
            }
        }

    }

    /**
     * 此处的title 是 frist的Group数据 和 headerTitle 一样
     * 第二view Title 未接触 Header  固定状态
     */
    fun secondTitleUntouchedHeader(
        title: Title,
        y: Float,
        headerTitle: Title,
        headerView: View,
        toolBar: View,
        callBack: HeaderCallBack<Title>
    ) {
        L.e("secondTitleUntouchedHeader ")
        if (null != headerTitle) {
            callBack.onHeaderCallBack(headerTitle)
            headerView.visibility = View.VISIBLE
        } else {
            headerView.visibility = View.INVISIBLE
        }
        if (headerTitle.isTop) {
//            toolBar.translationY = if (headerTitle.isTop) y + toolbarBottomY else 0f
            toolBar.translationY = 0f

            headerView.translationY = (-headerHeight).toFloat()

        } else {
            toolBar.translationY = 0f
            headerView.translationY = 0f
        }


    }

    /**
     * title 是 第二个的数据
     * headertitle  是当前吸顶的数据
     * 第二view Title 接触 Header
     */
    fun secondTitleTouchedHeader(
        title: Title,
        y: Float,
        headerTitle: Title?,
        headerView: View,
        toolBar: View,
        callBack: HeaderCallBack<Title>
    ) {
        L.e("secondTitleTouchedHeader ")
        if (null != headerTitle) {

            callBack.onHeaderCallBack(headerTitle)
            headerView.visibility = View.VISIBLE
        } else {
            headerView.visibility = View.INVISIBLE
        }
        if (title.isHeader) {
            if (title.isTop) {
                // 当两个top 相同时
                if (headerTitle?.name == title.name)
                    if (headerTitle.isTop) {
                        toolbar.translationY = (-headerHeight).toFloat()
                    }
                if (headerTitle?.isTop == true) {
                    headerView.translationY = (-headerHeight).toFloat()
                } else {
                    if (null == headerTitle) {
                        toolBar.translationY = (-headerHeight).toFloat()
                    } else {
                        headerView.translationY = y - headerHeight * 2
                        toolBar.translationY = 0f
                    }
                }
            } else {
                if (headerTitle?.isTop == true) {
                    headerView.translationY = (-headerHeight).toFloat()
                } else {
                    headerView.translationY = y - headerHeight * 2
                    toolBar.translationY = 0f
                }
            }
        }
    }

    /**
     *
     * 第二view Title 接触 Toolbar
     */
    fun secondTitleTouchedToolbar(
        view: View,
        title: Title?,
        y: Float,
        headerTitle: Title?,
        headerView: View,
        toolBar: View,
        callBack: HeaderCallBack<Title>
    ) {
        if (null != title) {
            callBack.onHeaderCallBack(title)
            headerView.visibility = View.VISIBLE
        } else {
            headerView.visibility = View.INVISIBLE
        }
        L.e("secondTitleTouchedToolbar ${title?.isTop == true} $title  yy $y headerTitle$headerTitle")
        if (title?.isTop == true) {
//            toolBar.visibility = View.INVISIBLE
            // y == 150  toolbarBottomY ==headerHeight== 150  y - headerHeight  = 0
            // y == 140   150        y - 150 = -10

            toolbar.alpha = ( y - toolbarBottomY) /headerHeight
            headerView.translationY = y - headerHeight
            onTopBarCallBack.callback(headerView, toolbarBottomY - y, title as TabSuckTop)
            // 当两个istop 数据 相同时
            if (headerTitle?.name ?: "" == title.name)
                if (headerTitle?.isTop == true) {
                    toolbar.alpha = (-headerHeight).toFloat()/headerHeight
//                    toolbar.translationY = (-headerHeight).toFloat()
                }

        } else {
            toolBar.alpha = 1f
//            toolbar.translationY = 0f
            headerView.translationY = 0f
        }

    }

    // 首先是 距离维度的判断
    /**
     * 第二view Title 未接触 Header  固定状态
     * 第二view Title 接触 Header
     * 第二view Title 接触 Toolbar
     * 第二view Title 滑过 Toolbar  固定状态   等于 第一个 因为第二view换view了
     */

    // 以下的是 距离的问题

    // 然后事当前 header 置顶类型判断
    /**
     * 是子类
     * 是title
     * 是置顶
     */

    // 然后事第二view 判断
    /**
     * 是子类
     * 是title
     * 是置顶
     */


    /**
     * 3*3*4 36 种场景
     *
     */
    private fun setHeaderData(data: String): String {
//        layout.visibility = if (isEmpty(data)) View.INVISIBLE else View.VISIBLE
        return data
    }

    interface HeaderCallBack<T> {
        fun onHeaderCallBack(t: T)
    }

    public fun isHeader(any: Any): Boolean {
        return if (any is Title) {
            return any.isHeader
        } else false
    }

    public fun isTop(any: Any): Boolean {
        return if (any is Title) {
            return any.isTop
        } else false
    }


    fun getTitle(index: Int): Title? {
        return if (index != -1) {
            items[index] as Title
        } else null
    }
}
