package com.iolll.liubo.crashhandler;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.telephony.TelephonyManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuBo on 2019/4/16.
 */
public class DeviceUtil {
    @SuppressLint("MissingPermission")
    public Map<String, String> getPhoneInfo(Context context) {
        Map<String, String> map = new HashMap<>();
        TelephonyManager phone = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        map.put("deviceId", phone.getDeviceId());
//        map.put("weiw",phone.get)
        return map;
    }
    //获取电池电量和充电状态
    public Map<String,String> getBattery(Context context) {
        Map<String,String> map = new HashMap<>();
        int battery = 0, chargeState = 0;
        Intent intent = context.registerReceiver(null,
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
            map.put("battery",String.valueOf(battery));
            map.put("status",String.valueOf(isCharging));
        }
        return map;
    }
}
