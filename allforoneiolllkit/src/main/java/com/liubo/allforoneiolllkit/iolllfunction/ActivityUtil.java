package com.liubo.allforoneiolllkit.iolllfunction;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by LiuBo on 2019/4/11.
 */
public class ActivityUtil {
    public static String getRunningActivityName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        return runningActivity;
    }
}
