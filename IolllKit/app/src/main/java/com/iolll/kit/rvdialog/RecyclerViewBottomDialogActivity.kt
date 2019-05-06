package com.iolll.kit.rvdialog

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.iolll.kit.R
import com.iolll.kit.Utils
import com.iolll.kit.click.TextItem
import com.iolll.kit.click.TextItemViewBinder
import com.iolll.liubo.bottomrecyclerdialog.RecyclerBottomDialog
import com.iolll.liubo.ifunction.IFunction
import com.iolll.liubo.iolllfunction.ClickListener
import com.iolll.liubo.niceutil.NiceUtil
import kotlinx.android.synthetic.main.activity_recycler_view_bottom_dialog.*
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

class RecyclerViewBottomDialogActivity : AppCompatActivity() {
    val items :Items = Items()
    var count: Int = 0
    val datas  = ArrayList<TextItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_bottom_dialog)
        count = if (itemCountEt.text.toString() == "") 0 else itemCountEt.text.toString().toInt()
        for (i in 0..count)
            datas.add(TextItem())
        items.addAll(datas)

        showBtn.setOnClickListener(ClickListener{v ->
            val adapter = MultiTypeAdapter()
            adapter.register(TextItem::class.java, TextItemViewBinder { self, t, position, viewHolder, view ->
                if (view != null) {
                    when (view.id) {
                        viewHolder.textName.id -> t.name = "iiiiiinin"
                        viewHolder.infoTv.id -> t.info = "卫计委"
                    }
                    datas[position] = t // 数据同步
                    TextItemViewBinder.setData(viewHolder, t)
                } else {
                    NiceUtil.forEach<TextView>(IFunction.Run<TextView> {
                        it.setOnClickListener(ClickListener { v -> self.onClick(self, t, position, viewHolder, v) })
                    }, viewHolder.textName, viewHolder.infoTv)
                }
            })

            var dialog = RecyclerBottomDialog.Builder<TextItem>(this)
                .setTitleName(titleNameEt.text.toString())
                .setAdapter(adapter)
                .setCancelBtnName(cancelBtnEt.text.toString())
                .setCancelListener { it.dismiss() }
                .setSubmitBtnName(submitBtnEt.text.toString())
                .setSubmitListener {
                    it.dismiss()
                    Utils.toast("www")
                }
                .setItems(items)
                .build()
            dialog.mIsBackGroudTransparent = round_rb.isChecked
            dialog.setmCancelable(!cantScrollCancel_rb.isChecked)
            dialog.showDialog()

        })
        resetBtn.setOnClickListener {
            datas.clear()
            count = if (itemCountEt.text.toString() == "") 0 else itemCountEt.text.toString().toInt()
            for (i in 0..count)
                datas.add(TextItem())
            items.clear()
            items.addAll(datas)
        }
//        showBtn.setOnClickListener {
//            val adapter = MultiTypeAdapter()
//            adapter.register(TextItem::class.java, TextItemViewBinder { self, t, position, viewHolder, view ->
//                if (view != null) {
//                    when (view.id) {
//                        viewHolder.textName.id -> t.name = "iiiiiinin"
//                        viewHolder.infoTv.id -> t.info = "卫计委"
//                    }
//                    datas[position] = t // 数据同步
//                    TextItemViewBinder.setData(viewHolder, t)
//                } else {
//                    NiceUtil.forEach<TextView>(IFunction.Run<TextView> {
//                        it.setOnClickListener(ClickListener { v -> self.onClick(self, t, position, viewHolder, v) })
//                    }, viewHolder.textName, viewHolder.infoTv)
//                }
//            })
//
//            var dialog = RecyclerBottomDialog.Builder<TextItem>(this)
//                .setTitleName(titleNameEt.text.toString())
//                .setAdapter(adapter)
//                .setItems(items)
//                .build()
//            dialog.mIsBackGroudTransparent = round_rb.isChecked
//            dialog.setmCancelable(!cantScrollCancel_rb.isChecked)
//            dialog.showDialog()
//        }
    }
}


