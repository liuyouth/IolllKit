package com.iolll.kit.router

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.iolll.kit.R
import com.iolll.kit.closingback.BaseRouterActivity
import kotlinx.android.synthetic.main.activity_router_main.*
@Route(path = "/route/main")
class RouterMainActivity : BaseRouterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_router_main)
        jumpBtn.setOnClickListener {
            routerNavigation(RouteAll.ROUTER_NICEBACK.uri).navigation()
        }
    }
}
