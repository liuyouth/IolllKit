package com.iolll.kit.closingback

import android.content.Intent
import android.os.Bundle
import com.iolll.kit.R
import com.iolll.liubo.niceclosingback.INiceBack
import com.iolll.liubo.niceclosingback.NiceClosingBack.ACTIVITY_FROM

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
    companion object {
        fun launchMe(niceBack: INiceBack){
            val intent  = Intent(niceBack.context,DetailActivity::class.java)
            intent.putExtra(ACTIVITY_FROM,niceBack.meName)
            niceBack.context.startActivity(intent)
        }
        fun launchBack(niceBack: INiceBack){
            val intent  = Intent(niceBack.context,DetailActivity::class.java)
            intent.putExtra(ACTIVITY_FROM,niceBack.meName)
            niceBack.context.startActivity(intent)
        }
    }
}
