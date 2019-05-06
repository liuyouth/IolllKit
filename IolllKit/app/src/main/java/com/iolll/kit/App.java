package com.iolll.kit;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import com.iolll.kit.closingback.DetailActivity;
import com.iolll.kit.closingback.IndexActivity;
import com.iolll.kit.closingback.ListActivity;
import com.iolll.kit.closingback.MeActivity;
import com.iolll.liubo.crashhandler.IolllCrashHandler;
import com.iolll.liubo.ifunction.IFunction;
import com.iolll.liubo.niceclosingback.INiceBack;
import com.iolll.liubo.niceclosingback.NiceClosingBack;

import java.util.ArrayList;
import java.util.HashMap;

import static com.iolll.liubo.niceclosingback.NiceClosingBack.EMPTYRUN;

/**
 * Created by LiuBo on 2019/4/11.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        NiceClosingBack.finishActionMap = new HashMap<String,ArrayList<NiceClosingBack.TargetRule>>(){{
            put(DetailActivity.class.getName(), new ArrayList<NiceClosingBack.TargetRule>() {{
                add(new NiceClosingBack.TargetRule(MeActivity.class.getName(), ListActivity.class.getName())); }});
            put(MeActivity.class.getName(), new ArrayList<NiceClosingBack.TargetRule>() {{
                add(new NiceClosingBack.TargetRule(IndexActivity.class.getName(), DetailActivity.class.getName())); }});
            put(IndexActivity.class.getName(), new ArrayList<NiceClosingBack.TargetRule>() {{
                add(new NiceClosingBack.TargetRule(ScrollingActivity.class.getName(), EMPTYRUN)); }});
        }};
        NiceClosingBack.finishStartActionMap = new HashMap<String,IFunction.Run<INiceBack>>(){{
            put(MeActivity.class.getName(), (IFunction.Run<INiceBack>) MeActivity.Companion::launchBack);
            put(IndexActivity.class.getName(), (IFunction.Run<INiceBack>) IndexActivity.Companion::launchBack);
            put(ScrollingActivity.class.getName(), (IFunction.Run<INiceBack>) iNiceBack -> {
                Intent intent = new Intent(iNiceBack.getContext(),ScrollingActivity.class);
                iNiceBack.getContext().startActivity(intent);
            });
        }};

//        IolllCrashHandler.INS.DataSaveCallBack(()->{
//
//        });

    }


    //获取电池电量和充电状态
    private int[] getBattery() {
        int battery = 0, chargeState = 0;
        Intent intent = registerReceiver(null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        if (intent != null && intent.getExtras() != null) {
            int level = intent.getIntExtra("level", 0);
            //电量的总刻度
            int scale = intent.getIntExtra("scale", 100);
            battery = (level * 100) / scale;
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;
            chargeState = isCharging ? 1 : 0;
        }
        return new int[]{battery, chargeState};
    }
}
