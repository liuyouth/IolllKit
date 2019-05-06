package com.iolll.kit.closingback

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import com.iolll.liubo.niceclosingback.INiceBack
import com.iolll.liubo.niceclosingback.NiceClosingBack
import com.iolll.liubo.niceclosingback.NiceClosingBack.ACTIVITY_FROM

open class BaseActivity : AppCompatActivity(), INiceBack {

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
    companion object {
        val TAG = "BaseAct";
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
}
