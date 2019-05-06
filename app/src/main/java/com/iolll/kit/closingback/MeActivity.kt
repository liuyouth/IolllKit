package com.iolll.kit.closingback

import android.content.Intent
import android.os.Bundle
import com.iolll.kit.R
import com.iolll.liubo.niceclosingback.INiceBack
import com.iolll.liubo.niceclosingback.NiceClosingBack.ACTIVITY_FROM

class MeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_me)
    }
    companion object {
        fun launchMe(niceBack: INiceBack){

            val intent  = Intent(niceBack.context,MeActivity::class.java)
            intent.putExtra(ACTIVITY_FROM,niceBack.meName)
            niceBack.context.startActivity(intent)
        }
        fun launchBack(niceBack: INiceBack){
            val intent  = Intent(niceBack.context,MeActivity::class.java)
            intent.putExtra(ACTIVITY_FROM,niceBack.meName)
            niceBack.context.startActivity(intent)
        }
    }
}
