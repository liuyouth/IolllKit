package com.iolll.kit.closingback

import android.content.Context
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.iolll.liubo.niceclosingback.INiceBack
import com.iolll.liubo.niceclosingback.NiceClosingBack
import com.iolll.liubo.niceclosingback.NiceClosingBack.ACTIVITY_FROM

open class BaseRouterActivity : AppCompatActivity(), INiceBack {

    private val fromActivityName: String by lazy { intent.getStringExtra(ACTIVITY_FROM) ?: "" }
    private val meActivityName: String = this.javaClass.name
    override fun getFromName(): String {
        return fromActivityName
    }
    override fun getContext(): Context {
        return this
    }
    override fun getMeName(): String {
        return meActivityName
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            finish()
            return false
        }
        return false
    }
    override fun finish() {
        Log.e(TAG,""+meActivityName)
        NiceClosingBack.finish(this)
        super.finish()
    }


    companion object {
        val TAG = "BaseAct"
    }

    fun routerNavigation(url:String): Postcard {
        return ARouter.getInstance().build(url).withString(ACTIVITY_FROM,meActivityName)
    }

}
