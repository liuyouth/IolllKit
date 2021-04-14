package com.iolll.kit.NiceAnimActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.iolll.kit.R
import kotlinx.android.synthetic.main.activity_nice_anim_main_empty.*

@Route(path = "/route/niceAnim")
class NiceAnimMainActivity : AppCompatActivity() {
    private var showSet: ConstraintSet = ConstraintSet()
    private var hideSet: ConstraintSet = ConstraintSet()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nice_anim_main_empty)
        hideSet.clone(constrain_root)
        showSet.clone(this, R.layout.activity_nice_anim_main)
        image.setOnClickListener {
            hideContent()
            constrain_root.postDelayed({  ARouter.getInstance().build("/route/niceAnim/info").navigation()}, 140)
        }
    }

    override fun onResume() {
        super.onResume()
        constrain_root.postDelayed({ showContent() }, 140)
    }
    override fun onStart() {
        super.onStart()

    }

    fun showContent() {
        TransitionManager.beginDelayedTransition(constrain_root)  // myView: 当前视图 ConstratintLayout 的 id
        showSet.applyTo(constrain_root)
    }

    fun hideContent() {
        TransitionManager.beginDelayedTransition(constrain_root)  // myView: 当前视图 ConstratintLayout 的 id
        hideSet.applyTo(constrain_root)
    }

    override fun finish() {
        hideContent()
        constrain_root.postDelayed({ super.finish() }, 140)

    }
}
