package com.iolll.kit

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.iolll.kit.defaultViewBinder.IntViewBinder
import com.iolll.kit.defaultViewBinder.IntegerViewBinder
import com.iolll.kit.defaultViewBinder.StringViewBinder
import com.iolll.kit.router.RouteAll
import com.iolll.kit.router.RouterInterceptor
import com.iolll.kit.router.RouterUtil
import com.iolll.liubo.bottomrecyclerdialog.RecyclerBottomDialog
import com.iolll.liubo.ifunction.IFunction
import com.iolll.liubo.iolllfunction.ClickListener
import com.iolll.liubo.iolllviewx.RecyclerView.ItemClick
import com.iolll.liubo.iolllviewx.RecyclerView.LinearLayoutManagerX
import com.iolll.liubo.iolllviewx.RecyclerView.StringItemViewBinder
import com.iolll.liubo.niceutil.NiceUtil
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

class ScrollingActivity : AppCompatActivity() {
    val items = Items()
//    companion object {
//        fun go(context:Context){
//            context.startActivity(Intent())
//        }
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
//        Utils.toast("this.getLocalClassName();" + this.javaClass.simpleName)
        val arraylist = ArrayList<String>()
        RouteAll.values().forEach {
            arraylist.add(it.uri)
        }

        arraylist.add("/wewe/qwe2")
        showRoutesTextResult(RouterUtil.textingRoutes(this, arraylist)!!)


        fab.setOnClickListener {
            throw RuntimeException("😈  啦啦啦啦啦~ ")
            //            ARouter.getInstance().build("/click/java/listview").navigation()
//            val intent = Intent(this, RecyclerViewBottomDialogActivity::class.java)
////            val intent = Intent(this,ClickKotlinActivity::class.java)
//            startActivity(intent)
//            while (1+1==2) {
//                Snackbar.make(view, "早啊凡凡 感冒好些了嘛", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//            }
        }
        val adapter = MultiTypeAdapter()

        adapter.register(RouteAll::class.java, RouteAllViewBinder { self, t, position, viewHolder, view ->
            if (null != view) {

                when (view.id) {
                    viewHolder.itemView.id -> routerNavigation(t.uri).navigation()
                    viewHolder.infoTv.id -> routerNavigation(t.uri).navigation()
                    viewHolder.textName.id -> routerNavigation(t.uri).navigation()
                }
            } else {
                NiceUtil.forEach<View>(IFunction.Run<View> {
                    it.setOnClickListener(ClickListener { v -> self.onClick(self, t, position, viewHolder, v) })
                }, viewHolder.textName, viewHolder.infoTv, viewHolder.itemView)
            }
        })
        adapter.register(String::class.java,
            object : StringViewBinder(ItemClick { self, t, position, viewHolder, view -> }) {})
        adapter.register(Int::class.java,
            object : IntViewBinder(ItemClick { self, t, position, viewHolder, view -> }) {})
        adapter.register(Integer::class.java,
            object : IntegerViewBinder(ItemClick { self, t, position, viewHolder, view -> }) {})
        items.addAll(RouteAll.values())
        adapter.items = items
        routeRv.layoutManager = LinearLayoutManagerX(this)
//        routeRv.addItemDecoration(
//            RVDis(
//                headerTv,
//                routeRv.layoutManager as LinearLayoutManagerX,
//                object : VirtualFamily {
//                    override fun isParentType(position: Int): Boolean {
//                        if (position < 0 || position >= items.size) {
//                            return false
//                        }
//                        return items[position] is String
//                    }
//
//                    override fun parentChildren(position: Int): Int {
//                        var viewType: Int
//                        var number = -1
//                        var pos = position
//                        do {
////                            getType(pos++)
//                            number++
//                        } while (items[pos++] !is String)
//                        return number
//                    }
//
//                    override fun isChildType(position: Int): Boolean {
//                        if (position < 0 || position >= items.size) {
//                            return false
//                        }
//                        return items[position] !is String
//                    }
//
//                    override fun childParentPosition(childPosition: Int): Int {
////                        var viewType: Int
//                        var position = childPosition
//                        if (childPosition > 0) {
//                            do {
////                                viewType = getType(--position)
//                            } while ( !getType(--position))
//                        }
//                        return position
//                    }
//                })
//        )
        routeRv.adapter = adapter

    }

    fun getType(index: Int): Boolean {
        return items[index] is String
    }

    fun showRoutesTextResult(noRealizeRouters: ArrayList<String>) {
        val items = Items()
        val adapter = MultiTypeAdapter()
        adapter.register(String::class.java, StringItemViewBinder { self, t, position, viewHolder, view ->

        })
        items.addAll(noRealizeRouters)
        val dialog = RecyclerBottomDialog.Builder<String>(this)
            .setItems(items)
            .setAdapter(adapter)
            .setTitleName("Route 异常提醒 缺少以下实现")
            .setCancelBtnName("尽快处理")
            .setSubmitBtnName("尽快处理")
            .build()
        dialog.mIsBackGroudTransparent = true
        dialog.setmCancelable(true)
//        dialog.titleBG = Utils.getColor(R.color.colorAccent)
        dialog.cancelTextColor = Utils.getColor(R.color.colorPrimary)
        dialog.submitTextColor = Utils.getColor(R.color.colorPrimary)
        dialog.showDialog()
        RouterInterceptor.isTextUrl = false // 一定要关闭 否则 所有路由都不会进行跳转
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun routerNavigation(url: String): Postcard {
        return ARouter.getInstance().build(url)
    }

}
