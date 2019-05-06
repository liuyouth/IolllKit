package com.iolll.kit.closingback

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.iolll.kit.R
import com.iolll.liubo.niceclosingback.INiceBack
import com.iolll.liubo.niceclosingback.NiceClosingBack.ACTIVITY_FROM

class IndexActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

    }
    public fun launchNext(view:View){
        ListActivity.launchMe(this)
    }

    companion object {
        fun launchMe(niceBack: INiceBack){

            val intent  = Intent(niceBack.context,IndexActivity::class.java)
            intent.putExtra(ACTIVITY_FROM,niceBack.meName)
            niceBack.context.startActivity(intent)
        }
        fun launchBack(niceBack: INiceBack){
            val intent  = Intent(niceBack.context,IndexActivity::class.java)
            intent.putExtra(ACTIVITY_FROM,niceBack.meName)
            niceBack.context.startActivity(intent)
        }
    }

}
