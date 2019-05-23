package com.iolll.kit.xidingRv

import android.graphics.Canvas
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iolll.liubo.bottomrecyclerdialog.DensityUtils
import com.iolll.liubo.niceutil.NiceUtil.isEmpty
import me.drakeet.multitype.MultiTypeAdapter

/**
 * Created by LiuBo on 2019-05-23.
 */
class RVDis(
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
                    val dataPos = if (isParentFirst) firstPos else virtualFamily.childParentPosition(firstPos)
                    println("dataPos $dataPos headerPosition $headerPosition  isTop ${virtualFamily.isParentType(dataPos)}")
                    if (virtualFamily.isParentType(firstPos)) {
                        val transY = -(DensityUtils.dp2px(parent.context, 100f) - (view.top - view.height).toFloat())
                        if (transY >= -DensityUtils.dp2px(parent.context, 50f)) {
                            layout.translationY = transY
                            if (headerPosition != virtualFamily.childParentPosition(firstPos-1)) {
                                root.let {
                                    val data = setHeaderData(
                                        with((parent.adapter as MultiTypeAdapter).items.get(virtualFamily.childParentPosition(firstPos-1))) {
                                            if (this is String) this else ""
                                        }
                                    )
                                    if (!isEmpty(data))
                                        root.text = data
                                }
                                headerPosition = virtualFamily.childParentPosition(firstPos-1)
                            }
                        } else {
                            layout.translationY = 0f
                            if (headerPosition != dataPos) {
                                root.let {
                                    val data = setHeaderData(
                                        with((parent.adapter as MultiTypeAdapter).items.get(dataPos)) {
                                            if (this is String) this else ""
                                        }
                                    )
                                    if (!isEmpty(data))
                                        root.text = data
                                }
                                headerPosition = dataPos
                            }
                        }
                    } else {
                        layout.translationY = 0f
                        if (headerPosition != dataPos) {
                            root.let {
                                val data = setHeaderData(
                                    with((parent.adapter as MultiTypeAdapter).items.get(dataPos)) {
                                        if (this is String) this else ""
                                    }
                                )
                                if (!isEmpty(data))
                                    root.text = data
                            }
                            headerPosition = dataPos
                        }


                    }
                } else if (view.top - view.height > DensityUtils.dp2px(parent.context, 100f)) {
                    val dataPos = virtualFamily.childParentPosition(firstPos - 1)
                    println(" 大于  dataPos $dataPos headerPosition $headerPosition")
                    layout.translationY = 0f
                    if (headerPosition != dataPos) {
                        root.let {
                            val data = setHeaderData(
                                with((parent.adapter as MultiTypeAdapter).items.get(dataPos)) {
                                    if (this is String) this else ""
                                }
                            )
                            if (!isEmpty(data))
                                root.text = data
                        }
                        headerPosition = dataPos
                    }
                } else {
                    val dataPos = virtualFamily.childParentPosition(firstPos - 1)
                    println("dataPos $dataPos headerPosition $headerPosition")
                    layout.translationY = 0f
                    if (headerPosition != dataPos) {
                        root.let {
                            val data = setHeaderData(
                                with((parent.adapter as MultiTypeAdapter).items.get(dataPos)) {
                                    if (this is String) this else ""
                                }
                            )
                            if (!isEmpty(data))
                                root.text = data
                        }
                        headerPosition = dataPos
                    }
                }
            }
        }

    }

    private fun setHeaderData(data: String): String {
//        layout.visibility = if (isEmpty(data)) View.INVISIBLE else View.VISIBLE
        return data
    }
}
