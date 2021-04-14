package com.iolll.liubo.crashhandler;

import android.content.Context;
import android.widget.Toast;
import com.iolll.liubo.ifunction.IFunction;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常捕捉Handler
 * 提供四个回调
 * Created by LiuBo on 2019/4/3.
 */
public enum IolllCrashHandler implements Thread.UncaughtExceptionHandler {
    INS;
    // 异常发生时

    public void setOnThrowableRun(IFunction.Run<Throwable> onThrowableRun) {
        this.onThrowableRun = onThrowableRun;
    }

    public void setOnThrowableMsgRun(IFunction.Run<ThrowableMsg> onThrowableMsgRun) {
        this.onThrowableMsgRun = onThrowableMsgRun;
    }

    public void setOnCrashEndRun(IFunction.NullRun onCrashEndRun) {
        this.onCrashEndRun = onCrashEndRun;
    }

    public void setOnUiRun(IFunction.NullRun onUiRun) {
        this.onUiRun = onUiRun;
    }

    private IFunction.Run<Throwable> onThrowableRun;
    // 异常信息回调
    private IFunction.Run<ThrowableMsg> onThrowableMsgRun;
    // 异常处理完毕后的回调
    private IFunction.NullRun onCrashEndRun = new IFunction.NullRun() {
        @Override
        public void run() {

        }
    };
    // 异常发生时的ui回调
    private IFunction.NullRun onUiRun = new IFunction.NullRun() {
        @Override
        public void run() {
            Toast.makeText(mContext, "小白🐱被😈抓走了！", Toast.LENGTH_SHORT).show();
        }
    };

    private IFunction.Run<Throwable> getSleep(final long i) {
        return new IFunction.Run<Throwable>() {
            @Override
            public void run(Throwable throwable) {
                // 程序休眠3s后退出
                try {
                    Thread.sleep(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }


    private static final String TAG = "AUVGoCrashHandler";
    /**
     * 系统默认的UncaughtException处理类
     **/
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * 程序context
     **/
    protected Context mContext;

    /**
     * 存储设备信息和异常信息
     **/
    private Map<String, String> mInfos = new HashMap<String, String>();

    /**
     * 设置crash文件位置
     **/
    private String mDRCrashFilePath;

    /**
     * 生成的crash文件
     **/
    private File crashFile;

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//        if (mDefaultHandler != null && !handlerException(ex)) {
//            mDefaultHandler.uncaughtException(thread, ex);
//        } else {
//
////            mContext.finishActivity();
//
//
////            ToastTop.INS.show();
////            Toast.makeText(mContext,"wwww",Toast.LENGTH_LONG).show();
////            System.exit(0);
//        }

        if (!handlerException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            // 当系统没有默认异常处理器的时候
//            Utils.startAct(WebActivity.class);
        }
        //所有处理器都处理完的时候
//        try {
//            Thread.sleep(1800);
//        } catch (InterruptedException e) {
//            Log.e(TAG, "uncaughtException: " + e.getMessage());
//            e.printStackTrace();
//        }
//        System.exit(0);
//        android.os.Process.killProcess(android.os.Process.myPid());
        if (null != onCrashEndRun)
            onCrashEndRun.run();

    }

    /**
     * 5、处理异常<br>
     * <br>
     * <p>
     * 5.1 收集设备参数信息<br>
     * 5.2 弹出窗口提示信息<br>
     * 5.3 保存log和crash到文件<br>
     * 5.4 发送log和crash到服务器<br>
     *
     * @param ex
     * @return 是否处理了异常
     */
    protected boolean handlerException(final Throwable ex) {

        if (ex == null) {
            return false;
        } else {
//            //使用Toast来显示异常信息
//            new Thread() {
//                @Override
//                public void run() {
//                    Looper.prepare();
//                    ex.printStackTrace();
//                    onUiRun.run();
//                    Looper.loop();
//                }
//            }.start();
            if (null != onUiRun)
                onUiRun.run();
            if (null != onThrowableRun)
                onThrowableRun.run(ex);

            // 5.1 收集设备参数信息
            collectDeviceInfo(mContext);
            // 5.3 保存log和crash到文件
            saveLogAndCrash(ex);
            // 5.4 发送log和crash到服务器
            sendLogAndCrash();

            return true;
        }
    }

    private void sendLogAndCrash() {
    }

    private void saveLogAndCrash(Throwable ex) {
//        ThrowableMsg throwableMsg = new ThrowableMsg(((InvocationTargetException) ex.getCause()).getTargetException());

        if (null != onThrowableMsgRun)
            onThrowableMsgRun.run(new ThrowableMsg(ex));
//        Log.e(TAG, "saveLogAndCrash: "+throwableMsg );
//        Log.e(TAG, "saveLogAndCrash: "+((InvocationTargetException)ex.getCause()).getTargetException().getMessage());
//        Log.e(TAG, "saveLogAndCrash: "+((InvocationTargetException)ex.getCause()).getTargetException().getStackTrace()[0].getClassName());
//        Log.e(TAG, "saveLogAndCrash: "+((InvocationTargetException)ex.getCause()).getTargetException().getStackTrace()[0].getMethodName());
//        Log.e(TAG, "saveLogAndCrash: "+((InvocationTargetException)ex.getCause()).getTargetException().getStackTrace()[0].getLineNumber());

//        Log.e(TAG, "saveLogAndCrash: "+ex.getStackTrace()[0].getMethodName());
//        Log.e(TAG, "saveLogAndCrash: "+ex.getStackTrace()[0].getLineNumber());
    }

    private void collectDeviceInfo(Context mContext) {

    }
}
