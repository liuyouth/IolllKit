package com.iolll.kit.TagView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.iolll.kit.R
import com.liubo.allforoneiolllkit.iolllviewx.RecyclerView.ItemClick
import kotlinx.android.synthetic.main.activity_tag_view.*
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter

@Route(path = "/route/tag")
class TagViewActivity : AppCompatActivity() {
    private val adapter = MultiTypeAdapter()
    private val items = Items()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_view)
        text1.text = "hahahhahahha"
        dynmicTagLayout.setData(arrayListOf<String>("无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4"
            ,"我是",""
            ,"w","f","werkrjkwjerlkj","瓦尔基开金融界").toList())
        adapter.register(TagCell::class.java,TagCellViewBinder(ItemClick { self, t, position, viewHolder, view -> {

        }}))
        items.add(TagCell("天",arrayListOf<String>("无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4"
            ,"我是",""
            ,"w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()))
        items.add(TagCell(
            "天", arrayListOf<String>("1无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天问问全额翁群二无", arrayListOf<String>("2无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天", arrayListOf<String>("3无自费","无复用","接送机","哈哈liuuuiugohg[oih哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天切尔奇无切尔奇翁二", arrayListOf<String>("4无自费","无复lkk';lkp'jpo[p]p[用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天", arrayListOf<String>("5无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天卫栖梧翁", arrayListOf<String>("6无自费","无复用","接;k;'l';k';';;'l;送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天", arrayListOf<String>("7无自费","无复用","接oiui[po[o][o][送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天悄悄问翁二翁二", arrayListOf<String>("8无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天", arrayListOf<String>("9无自费","无复用o[p[p[p[p[p[]][p[]p[]o","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天678687687687687687", arrayListOf<String>("10无自费","无8yoiip[op[o[o][复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天uytfjhljlj;l;lk;lk", arrayListOf<String>("11无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天", arrayListOf<String>("12无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天", arrayListOf<String>("13无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天问问全额翁群二无", arrayListOf<String>("无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天", arrayListOf<String>("无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天切尔奇无切尔奇翁二", arrayListOf<String>("无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天", arrayListOf<String>("无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天卫栖梧翁", arrayListOf<String>("无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天", arrayListOf<String>("无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天悄悄问翁二翁二", arrayListOf<String>("无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天", arrayListOf<String>("无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天", arrayListOf<String>("无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        items.add(TagCell(
            "天", arrayListOf<String>("无自费","无复用","接送机","哈哈哈","我是谁我在那里哈4","我是","","w","f","werkrjkwjerlkj","瓦尔基开金融界").toList()
        ))
        adapter.items = items
        rv.layoutManager =  LinearLayoutManager(this)
        rv.adapter = adapter



    }
}
