package com.iolll.kit.router

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Created by LiuBo on 2019-05-09.
 */
public class RouterUtil {
    companion object {
        fun textingRoutes(
            context: Context,
            arraylist: ArrayList<String>
        ): java.util.ArrayList<String>? {
            // 循环遍历 后台返回的所有路由 进行测试检测是否可以被跳转
            // 必须将 RouterInterceptor.isTextUrl 设置为 true  否则会真实跳转
            RouterInterceptor.isTextUrl = true
            RouterInterceptor.noRealizeRouters.clear()
            for (s in arraylist){
                ARouter.getInstance().build(s).navigation(context, callback)
            }
//            ARouter.getInstance().build("/click/java/listview").navigation(context, callback)
//            ARouter.getInstance().build(RouteAll.ROUTER_MAIN.uri).navigation(context, callback)
//            ARouter.getInstance().build(RouteAll.ROUTER_NICEBACK.uri).navigation(context, callback)

            // 检测是否所有路由都被实现没有实现的进行弹窗提示
            // 检测完毕后 需要将 RouterInterceptor.isTextUrl 设置为 false 否则路由将不会跳转
//            println(RouterInterceptor.noRealizeRouters.size)
//            RouterInterceptor.isTextUrl = false
            return RouterInterceptor.noRealizeRouters
        }

        val callback: NavigationCallback = object : NavigationCallback {
            override fun onLost(p0: Postcard?) {
                RouterInterceptor.noRealizeRouters.add(p0?.path.toString())
            }

            override fun onFound(p0: Postcard?) {

            }

            override fun onInterrupt(p0: Postcard?) {

            }

            override fun onArrival(p0: Postcard?) {

            }
        }
    }

}
