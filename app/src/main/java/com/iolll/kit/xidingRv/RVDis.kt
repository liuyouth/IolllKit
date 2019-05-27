package com.iolll.kit.xidingRv

import android.graphics.Canvas
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iolll.ki.RVDis2
import com.iolll.liubo.bottomrecyclerdialog.DensityUtils
import com.iolll.liubo.niceutil.NiceUtil.isEmpty
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Created by LiuBo on 2019-05-23.
 */
class RVDis(
    private val toolbar: LinearLayout,
    private val layout: LinearLayout,
    private val root: TextView,
    private var layoutManage: LinearLayoutManager,
    private var virtualFamily: VirtualFamily
) : RecyclerView.ItemDecoration() {
    private var headerPosition: Int = -1

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val firstPos = layoutManage.findFirstVisibleItemPosition() + 1
        val isParentFirst = virtualFamily.isParentType(firstPos)
        val isChildFirst = virtualFamily.isChildType(firstPos)
        println("firstPos $firstPos isPaF $isParentFirst isChildF $isChildFirst")

        if (!isParentFirst && !isChildFirst) {
            layout.visibility = View.INVISIBLE
        } else {
            val view = layoutManage.findViewByPosition(firstPos + 1)
//            layoutManage.top
            with(view) {
                if (this != null) {
                    println("yy ${this.scrollY}  yy ${this.y}  top ${this.top}    fixTop ${this.top - this.height}")
                }
            }
            if (null != view) {
                if (view.top - view.height <= DensityUtils.dp2px(parent.context, 100f)) {
                    // 偏移量在 规定范围内
                    val dataPos = if (isParentFirst) firstPos else virtualFamily.childParentPosition(firstPos)
                    println("222dataPos $dataPos headerPosition $headerPosition  isTItle ${virtualFamily.isParentType(dataPos)}")
                    if (virtualFamily.isParentType(firstPos)) {
                        // 如果该项是组名的情况
                        val transY = -(DensityUtils.dp2px(parent.context, 100f) - (view.top - view.height).toFloat())
                        if (transY >= -DensityUtils.dp2px(parent.context, 50f)) {
// 此处处理底部置顶Header 接触事件


                            if ((parent.adapter as MultiTypeAdapter).items.get(dataPos) is Title) {
                                // 必须是title 类型
                                if (((parent.adapter as MultiTypeAdapter).items.get(dataPos) as Title).isTop) {
                                   toolbar.translationY = transY - DensityUtils.dp2px(parent.context,50F).toFloat()
//                                    layout.translationY = transY - DensityUtils.dp2px(parent.context,50f).toFloat()
                                }else{
                                    toolbar.translationY = 0f
                                    layout.translationY = transY
                                }
                            }else{
                                toolbar.translationY = 0f
                                layout.translationY = 0f
                            }


                            if (headerPosition != virtualFamily.childParentPosition(firstPos - 1)) {
                                root.let {
                                    val data = setHeaderData(
                                        with(
                                            (parent.adapter as MultiTypeAdapter).items.get(
                                                virtualFamily.childParentPosition(
                                                    firstPos - 1
                                                )
                                            )
                                        ) {
                                            if (this is Title) name else ""
                                        }
                                    )
                                    if (!isEmpty(data))
                                        root.text = data
                                }
                                headerPosition = virtualFamily.childParentPosition(firstPos - 1)
                            }
                        } else {
// 此处header 第二阶段 Top

                            if ((parent.adapter as MultiTypeAdapter).items.get(firstPos) is Title) {
                                // 必须是title 类型
                                if (((parent.adapter as MultiTypeAdapter).items.get(firstPos) as Title).isTop) {
                                    //必须事置顶类型
                                    println("trainy $transY")
                                    layout.translationY = transY
                                    toolbar.translationY = transY + DensityUtils.dp2px(parent.context, 50f)
                                    if (headerPosition != dataPos) {
                                        root.let {
                                            val data = setHeaderData(
                                                with((parent.adapter as MultiTypeAdapter).items.get(dataPos)) {
                                                    if (this is Title) name else ""
                                                }
                                            )
                                            if (!isEmpty(data))
                                                root.text = data
                                        }
                                        headerPosition = dataPos
                                    }
                                } else {
                                    // 不涉及 toolbar
                                    layout.translationY = 0f
                                    toolbar.translationY = 0f
                                    if (headerPosition != dataPos) {
                                        root.let {
                                            val data = setHeaderData(
                                                with((parent.adapter as MultiTypeAdapter).items.get(dataPos)) {
                                                    if (this is Title) name else ""
                                                }
                                            )
                                            if (!isEmpty(data))
                                                root.text = data
                                        }
                                        headerPosition = dataPos
                                    }

                                }
                            } else {
                                // 不是组名情况
                                layout.translationY = 0f
                                toolbar.translationY = 0f
                                if (headerPosition != dataPos) {
                                    root.let {
                                        val data = setHeaderData(
                                            with((parent.adapter as MultiTypeAdapter).items.get(dataPos)) {
                                                if (this is Title) name else ""
                                            }
                                        )
                                        if (!isEmpty(data))
                                            root.text = data
                                    }
                                    headerPosition = dataPos
                                }
                            }


                        }
                    } else {
                        // 不是组名的情况
                        layout.translationY = 0f
                        toolbar.translationY = 0f
                        if (headerPosition != dataPos) {
                            root.let {
                                val data = setHeaderData(
                                    with((parent.adapter as MultiTypeAdapter).items.get(dataPos)) {
                                        if (this is Title) name else ""
                                    }
                                )
                                if (!isEmpty(data))
                                    root.text = data
                            }
                            headerPosition = dataPos
                        }


                    }
                } else if (view.top - view.height > DensityUtils.dp2px(parent.context, 100f)) {
                    // 偏移量在规定范围外
                    val dataPos = virtualFamily.childParentPosition(firstPos - 1)
                    println(" 大于  dataPos $dataPos headerPosition $headerPosition")

                    val item = (parent.adapter as me.drakeet.multitype.MultiTypeAdapter).items.get(dataPos)
                    if (item is Title) {
                        val title = item as Title
                        if (title.isTop) {
                            toolbar.translationY = -DensityUtils.dp2px(parent.context, 50f).toFloat()
                            layout.translationY = -DensityUtils.dp2px(parent.context, 50f).toFloat()
                        } else {
                            toolbar.translationY = 0f
                            layout.translationY = 0f
                        }
                    } else {
                        toolbar.translationY = 0f
                        layout.translationY = 0f
                    }
                    if (headerPosition != dataPos) {
                        root.let {
                            val data = setHeaderData(
                                if (item is Title) item.name else ""
                            )
                            if (!isEmpty(data)) {
                                root.text = data
                            }
                        }
                        headerPosition = dataPos
                    }
//                    if (headerPosition != dataPos) {
//                        root.let {
//                            val data = setHeaderData(
//                                with((parent.adapter as MultiTypeAdapter).items.get(dataPos)) {
//                                    if (this is Title) name else ""
//                                }
//                            )
//                            if (!isEmpty(data)) {
//                                root.text = data
//                            }
//                        }
//                        headerPosition = dataPos
//                    }
                }
            }
        }

    }

    /**
     * 第二view Title 未接触 Header  固定状态
     */
    fun secondTitleUntouchedHeader(first:Int,y:Float,headerView:View,toolBar:View,callBack: RVDis2.HeaderCallBack<Title>){


    }

    /**
     * 第二view Title 接触 Header
     */
    fun secondTitleTouchedHeader(){

    }
    /**
     * 第二view Title 接触 Toolbar
     */
    fun secondTitleTouchedToolbar(){

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

}
