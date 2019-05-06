package com.iolll.kit.closingback

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.iolll.kit.R
import com.iolll.liubo.niceclosingback.INiceBack
import com.iolll.liubo.niceclosingback.NiceClosingBack.ACTIVITY_FROM

class ListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

    }
    public fun launchNext(view: View){
        DetailActivity.launchMe(this)
    }
    companion object {
        public fun launchMe(niceBack: INiceBack){
            val intent  = Intent(niceBack.context,ListActivity::class.java)
            intent.putExtra(ACTIVITY_FROM,niceBack.meName)
            niceBack.context.startActivity(intent)
        }
    }
}
