package com.iolll.kit.click;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.iolll.kit.BaseActivity;
import com.iolll.kit.R;
import com.iolll.liubo.niceutil.NiceUtil;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
@Route(path = "/click/java/listview")
public class ClickJavaActivity extends BaseActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private Items items = new Items();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        items.add(new TextItem());
        items.add(new TextItem());
        items.add(new TextItem());
        items.add(new TextItem());
        items.add(new TextItem());
        items.add(new TextItem());
        items.add(new TextItem());
        items.add(new TextItem());
        items.add(new TextItem());
        items.add(new TextItem());
        items.add(new TextItem());
        items.add(new TextItem());
        items.add(new TextItem());
        items.add(new TextItem());
        items.add(new TextItem());
        adapter.setItems(items);
        adapter.register(TextItem.class, new TextItemViewBinder((self, textItem, position, viewHolder, view) -> {
            if (view != null) {
                if (view.getId() == viewHolder.infoTv.getId()) {
                    textItem.setInfo("iiiiiinin");
                } else if (view.getId() == viewHolder.textName.getId()) {
                    textItem.setName("卫计委");
                }
                TextItemViewBinder.setData(viewHolder, textItem);
            } else {
                NiceUtil.forEach(viewHolderV -> viewHolderV.setOnClickListener(v -> self.onClick(self, textItem, position, viewHolder, v)),
                        viewHolder.textName, viewHolder.infoTv);
            }
        }));
    }
}
