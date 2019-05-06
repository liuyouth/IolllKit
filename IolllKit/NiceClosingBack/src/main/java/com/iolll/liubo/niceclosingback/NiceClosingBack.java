package com.iolll.liubo.niceclosingback;

import android.app.Activity;
import com.iolll.liubo.ifunction.AnyRun;
import com.iolll.liubo.ifunction.IFunction;
import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.text.TextUtils.isEmpty;
import static com.iolll.liubo.niceutil.NiceUtil.isNotNull;

/**
 * Created by LiuBo on 2019/4/12.
 */
public class NiceClosingBack {
    public static String ACTIVITY_FROM = "ACTIVITY_FROM";
    public static String EMPTYRUN = "emptyRun";
    /**
     * 传入实现INiceBack的Activity
     *
     * @param iNiceBack
     */
    public static void finish(INiceBack iNiceBack) {
        if (isNotNull(iNiceBack.getFromName(), iNiceBack.getMeName()))
            AnyRun.ins(finishActionMap.get(iNiceBack.getMeName())).whenNotNull(wantStartName ->
                    Observable.fromIterable(wantStartName).filter(targetRule ->
                            isEmpty(iNiceBack.getFromName()) ? EMPTYRUN.equals(targetRule.fromActivity) :
                                    (EMPTYRUN.equals(targetRule.fromActivity) || iNiceBack.getFromName().equals(targetRule.fromActivity)))
                            .subscribe(targetRule ->
                                            AnyRun.ins(finishStartActionMap.get(targetRule.wantActivity)).whenNotNull(contextRun -> {
                                                contextRun.run(iNiceBack);
                                                ((Activity) iNiceBack).overridePendingTransition(R.anim.slide_in_left_speed, android.R.anim.slide_out_right);
                                            })
                                    , Throwable::printStackTrace));
    }

    public static String getName(String allName) {
        return allName.substring(allName.lastIndexOf(".") + 1);
    }

    /**
     * 这样这个数据就可以通过接口获取了
     */
    public static Map<String, ArrayList<TargetRule>> finishActionMap = new HashMap<String, ArrayList<TargetRule>>() {{
//        put(CarOrderDetailActivity.class.getName(), new ArrayList<TargetRule>() {{
//            add(new TargetRule(CarOrderListActivity.class.getName(), CarBookActivity.class.getName()));
//        }});
//        put(HotelOrderDetailActivity.class.getName(), new ArrayList<TargetRule>() {{
//            add(new TargetRule(OrderListActivity.class.getName(), HotelBookActivity.class.getName()));
//        }});
//        put(PlaneOrderDetailActivity.class.getName(), new ArrayList<TargetRule>() {{
//            add(new TargetRule(OrderListActivity.class.getName(), PlaneBook2Activity.class.getName()));
//        }});
//
//        put(CarOrderListActivity.class.getName(),new ArrayList<TargetRule>(){{
//            add(new TargetRule(MainActivity.class.getName(),"emptyRun"));
//        }});
//        put(OrderListActivity.class.getName(), new ArrayList<TargetRule>() {{
//            add(new TargetRule(MainActivity.class.getName(), "emptyRun"));
//        }});
    }};

    public static class TargetRule {
        private String wantActivity;
        private String fromActivity; // 如果配置的emptyRun  就是不论谁过来都进行拦截

        public TargetRule(String carOrderListActivity, String f) {
            wantActivity = carOrderListActivity;
            fromActivity = f;
        }

        public String getWantActivity() {
            return wantActivity;
        }

        public void setWantActivity(String wantActivity) {
            this.wantActivity = wantActivity;
        }

        public String getFromActivity() {
            return fromActivity;
        }

        public void setFromActivity(String fromActivity) {
            this.fromActivity = fromActivity;
        }
    }

    /**
     * wantActivity 目标页面的启动方式  无参数
     * 有参数的等待// Todo
     * 本地就维护这一个仿Activity启动的map数据
     * 仅仅是启动 并不能携带参数
     */
    public static Map<String, IFunction.Run<INiceBack>> finishStartActionMap = new HashMap<String, IFunction.Run<INiceBack>>() {{
//        put(IndexActivity.class.getName(), (IFunction.Run<INiceBack>) IndexActivity::launch);
//        put(CarOrderListActivity.class.getName(), (IFunction.Run<INiceBack>) CarOrderListActivity::launchBack);
//        put(OrderListActivity.class.getName(), (IFunction.Run<INiceBack>) OrderListActivity::launch);
    }};

}
