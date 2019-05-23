package com.iolll.kit;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by LiuBo on 2019/4/11.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        Utils.init(this);
        if (Utils.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
//            ARouter.openLog();     // 打印日志
//            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

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
