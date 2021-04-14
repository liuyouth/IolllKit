package com.iolll.kit.FilterBar

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.iolll.kit.FilterBar.using.StarPrice
import com.iolll.kit.FilterBar.using.StarPriceFilterBarViewBinder
import com.iolll.kit.FilterBar.using.TitleFilterBarViewBinder
import com.iolll.liubo.niceutil.NiceUtil
import kotlinx.android.synthetic.main.activity_filter.*

@Route(path = "/route/filter")
class FilterActivity : AppCompatActivity() {
    private var mConstraintSet1: ConstraintSet? = null
    private var mConstraintSet2: ConstraintSet? = null
    private var mConstraintReset: ConstraintSet? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.iolll.kit.R.layout.activity_filter)
        tuijian_btn.setOnClickListener {
            showFilterContent("tuijian")
        }
        mConstraintSet1 = ConstraintSet()
        mConstraintSet2 = ConstraintSet()


        //把默认 constraintLayout 布局放到 mConstraintSet1 中
        mConstraintSet1!!.clone(filter_bar_layout)
        //把标定位置变换的 constraintLayout 布局放到 mConstraintSet2 中
        mConstraintSet2!!.clone(filter_bar_layout)
        mConstraintSet2!!.clear(com.iolll.kit.R.id.filter_content_layout)
        mConstraintSet2!!.connect(
            com.iolll.kit.R.id.filter_content_layout, ConstraintSet.BOTTOM,
            com.iolll.kit.R.id.filter_bar, ConstraintSet.BOTTOM
        )
        mConstraintSet2!!.connect(
            com.iolll.kit.R.id.filter_content_layout,
            ConstraintSet.LEFT,
            ConstraintSet.PARENT_ID,
            ConstraintSet.LEFT
        )
        mConstraintSet2!!.connect(
            com.iolll.kit.R.id.filter_content_layout,
            ConstraintSet.RIGHT,
            ConstraintSet.PARENT_ID,
            ConstraintSet.RIGHT
        )

        filter_barr.register(StarPrice::class.java, StarPriceFilterBarViewBinder())
        filter_barr.setDeepCopyCallBack { NiceUtil.sonBeSuAutoper(it, it.javaClass) }
        filter_barr.titleViewBinder = TitleFilterBarViewBinder()
        filter_barr.addData(StarPrice("aaaaa1\ndawew\nweweaw\nweaea\n\n\n\n\n\n\n\nerrr"))
        filter_barr.addData(StarPrice("aaaaa1\n" +
                "dawew\n" +
                "weweaw\n" +
                "weaea\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "errr"))
        filter_barr.addData(StarPrice("aaaaa3"))
        filter_barr.addData(StarPrice("aaaaa4"))
        filter_barr.post {
            filter_barr.notifyDataSetChanged()
        }
    }

    var isShow: Boolean = false;
    fun showFilterContent(name: String) {
        filter_content.removeAllViews()
        filter_content.addView(
            LayoutInflater.from(filter_content_layout.getContext()).inflate(
                com.iolll.kit.R.layout.view_shaixuan,
                null
            )
        )
//        var viewHeight = filter_content.layoutParams.height
//        val outMetrics = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(outMetrics)
//        val height = outMetrics.heightPixels
//        viewHeight = if (height*0.6 <viewHeight) (height*0.6).toInt() else viewHeight
//        var p  =filter_content_layout.layoutParams
//        p.height= viewHeight
//        filter_content_layout.layoutParams = p
//        println("  wwwww "+height*0.6+ "  v "+viewHeight + "h "+height  )


        if (isShow) {
            filter_bar_layout.setBackgroundColor(Color.parseColor("#00585858"))
            TransitionManager.beginDelayedTransition(filter_bar_layout);// 动画效果

            mConstraintSet2?.applyTo(filter_bar_layout);
        } else {
            filter_bar_layout.setBackgroundColor(Color.parseColor("#58585858"))
            TransitionManager.beginDelayedTransition(filter_bar_layout);// 动画效果
            mConstraintSet1?.applyTo(filter_bar_layout);
        }
        isShow = !isShow
    }
}
