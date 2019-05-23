package com.iolll.kit.closingback;

import android.content.Intent;
import com.iolll.kit.ScrollingActivity;
import com.iolll.liubo.ifunction.IFunction;
import com.iolll.liubo.niceclosingback.INiceBack;
import com.iolll.liubo.niceclosingback.NiceClosingBack;

import java.util.ArrayList;
import java.util.HashMap;

import static com.iolll.liubo.niceclosingback.NiceClosingBack.EMPTYRUN;

/**
 * 所有Activity 返回规则页面
 * Created by LiuBo on 2019-05-07.
 */
public class AllActivitysConfig {
    public static void init() {
        // 可从云端获取规则
        NiceClosingBack.finishActionMap = new HashMap<String, ArrayList<NiceClosingBack.TargetRule>>() {{
            put(DetailActivity.class.getName(), new ArrayList<NiceClosingBack.TargetRule>() {{
                add(new NiceClosingBack.TargetRule(MeActivity.class.getName(), ListActivity.class.getName()));
            }});
            put(MeActivity.class.getName(), new ArrayList<NiceClosingBack.TargetRule>() {{
                add(new NiceClosingBack.TargetRule(IndexActivity.class.getName(), DetailActivity.class.getName()));
            }});
            put(IndexActivity.class.getName(), new ArrayList<NiceClosingBack.TargetRule>() {{
                add(new NiceClosingBack.TargetRule(ScrollingActivity.class.getName(), EMPTYRUN));
            }});
        }};


        // 本地返回某页面实现
        NiceClosingBack.finishStartActionMap = new HashMap<String, IFunction.Run<INiceBack>>() {{
            put(MeActivity.class.getName(), (IFunction.Run<INiceBack>) MeActivity.Companion::launchBack);
            put(IndexActivity.class.getName(), (IFunction.Run<INiceBack>) IndexActivity.Companion::launchBack);
            put(ScrollingActivity.class.getName(), (IFunction.Run<INiceBack>) iNiceBack -> {
                Intent intent = new Intent(iNiceBack.getContext(), ScrollingActivity.class);
                iNiceBack.getContext().startActivity(intent);
            });
        }};
    }
}
