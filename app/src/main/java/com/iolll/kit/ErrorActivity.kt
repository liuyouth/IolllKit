package com.iolll.kit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iolll.liubo.crashhandler.ThrowableMsg
import kotlinx.android.synthetic.main.activity_error.*

class ErrorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)

        error_text.text = SPUtils.getInstance().getString(ThrowableMsg::class.java!!.getCanonicalName())
    }
}
