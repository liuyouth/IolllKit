package com.iolll.kit.NiceAnimActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.AutoTransition
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_nice_anim_main_empty.*


@Route(path = "/route/niceAnim/info")
class NiceInfoActivity : AppCompatActivity() {
    private var showSet: ConstraintSet = ConstraintSet()
    private var hideSet: ConstraintSet = ConstraintSet()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.iolll.kit.R.layout.activity_nice_info_empty)
        hideSet.clone(constrain_root)
        showSet.clone(this, com.iolll.kit.R.layout.activity_nice_info)
    }

    override fun onResume() {
        super.onResume()
        constrain_root.postDelayed({ showContent() }, 250)
    }
    override fun onStart() {
        super.onStart()
    }

    fun showContent() {
        val autoTransition = AutoTransition()
        autoTransition.addListener(object : Transition.TransitionListener{
            override fun onTransitionEnd(transition: Transition) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionResume(transition: Transition) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionPause(transition: Transition) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionCancel(transition: Transition) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionStart(transition: Transition) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        autoTransition.duration = 250
        TransitionManager.beginDelayedTransition(constrain_root,autoTransition)  // myView: 当前视图 ConstratintLayout 的 id

        showSet.applyTo(constrain_root)
    }

    fun hideContent() {
        val autoTransition = AutoTransition()
        autoTransition.addListener(object : Transition.TransitionListener{
            override fun onTransitionEnd(transition: Transition) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionResume(transition: Transition) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionPause(transition: Transition) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionCancel(transition: Transition) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTransitionStart(transition: Transition) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        autoTransition.duration = 250
        TransitionManager.beginDelayedTransition(constrain_root,autoTransition)  // myView: 当前视图 ConstratintLayout 的 id
        hideSet.applyTo(constrain_root)
    }

    override fun finish() {
        hideContent()
        constrain_root.postDelayed({ super.finish() }, 250)

    }

}
