package com.iolll.kit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import com.google.gson.Gson;
import com.iolll.kit.closingback.AllActivitysConfig;
import com.iolll.liubo.crashhandler.IolllCrashHandler;
import com.iolll.liubo.niceutil.GsonFactory;
import com.iolll.liubo.niceutil.NiceContextCallBack;
import com.iolll.liubo.niceutil.NiceUtil;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import static com.iolll.liubo.niceutil.NiceUtil.isEmpty;
import static com.iolll.liubo.niceutil.NiceUtil.isNotNull;

/**
 * Created by LiuBo on 2019/4/11.
 */
public class Utils {
    //    Toast
    public static Gson gson = GsonFactory.create();
    private static Context context;
    private static Toast toast = null;
    public static boolean DEBUG;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

    public static String getString(@StringRes int resId) {
        return context.getString(resId);
    }


    public static void startAct(Class toclass) {
        Intent intent = new Intent(context, toclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(intent);
    }


    public static void startAct(Class toclass, Bundle bundle) {
        Intent intent = new Intent(context, toclass);
        intent.putExtra("bundle", bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(intent);
    }


    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    static WeakReference<Activity> sTopActivityWeakRef;
    static List<Activity> sActivityList = new LinkedList<>();

    private static Application.ActivityLifecycleCallbacks mCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            sActivityList.add(activity);
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            sActivityList.remove(activity);
        }
    };


    /**
     * 初始化工具类
     *
     * @param app 应用
     */
    public static void init(@NonNull final Application app) {
        Utils.sApplication = app;
        context = app;
        NiceUtil.INS.init(new NiceContextCallBack() {
            @Override
            public Gson gson() {
                return gson;
            }

            @Override
            public void toast(String s) {
                Utils.toast(s);
            }

            @Override
            public void toast(int s) {
                Utils.toast(s);
            }

            @Override
            public void longToast(String s) {
                Utils.longToast(s);
            }

            @Override
            public void longToast(int s) {
                Utils.longToast(s);
            }
        });
        app.registerActivityLifecycleCallbacks(mCallbacks);
        AllActivitysConfig.init();// 加载页面配置文件
        IolllCrashHandler.INS.init(context);
        DEBUG = BuildConfig.DEBUG;
    }

    /**
     * 获取Application
     *
     * @return Application
     */
    public static Application getApp() {
        if (sApplication != null) return sApplication;
        throw new NullPointerException("u should init first");
    }

    private static void setTopActivityWeakRef(Activity activity) {
        if (sTopActivityWeakRef == null || !activity.equals(sTopActivityWeakRef.get())) {
            sTopActivityWeakRef = new WeakReference<>(activity);
        }
    }


    public static boolean handlefilter(boolean isShow, String msg) {
//        if (ThreadUtil.isOnMainThread())
        if (isShow)
            toast(msg);
        return !isShow;
    }

    public static boolean handlefilterIsEmpty(String text, String msg) {
        boolean isShow = isEmpty(text);
//        if (ThreadUtil.isOnMainThread())
        if (isShow)
            toast(msg);
        return !isShow;
    }


    public static void toast(String s) {
        showToast(s, Toast.LENGTH_SHORT);
    }

    public static void toast(int s) {
        showToast(getContext().getString(s), Toast.LENGTH_SHORT);
    }

    public static void longToast(String s) {
        showToast(s, Toast.LENGTH_LONG);
    }

    public static void longToast(int s) {
        showToast(getContext().getString(s), Toast.LENGTH_LONG);
    }

    private static void showToast(String msg, int length) {
        System.out.println(" showToast");
        if (toast == null) {
            toast = Toast.makeText(context, msg, length);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    public static <D> D isNullDefault(D appoint, D defaultObject) {
        return isNotNull(appoint) ? appoint : defaultObject;
    }


    public static int getColor(int color_id) {
        return getContext().getResources().getColor(color_id);
    }

    public static Drawable getDrawable(int imgId) {
        Drawable d = getContext().getResources().getDrawable(imgId);
        /// 这一步必须要做,否则不会显示.
        d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
        return d;

    }

}
