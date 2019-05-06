package com.iolll.liubo.crashhandler;

import android.content.Context;
import com.iolll.liubo.ifunction.IFunction;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuBo on 2019/4/3.
 */
public enum IolllCrashHandler implements Thread.UncaughtExceptionHandler {
    INS;
    private IFunction.Run<Throwable> onThrowableRun;
    private IFunction.Run<ThrowableMsg> onThrowableMsgRun ;
    private IFunction.Run<Throwable> onCrashRun = getSleep(1000);
    private IFunction.NullRun onCrashEndRun = new IFunction.NullRun() {
        @Override
        public void run() {

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
    /** 系统默认的UncaughtException处理类 **/
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /** 程序context **/
    protected Context mContext;

    /** 存储设备信息和异常信息 **/
    private Map<String, String> mInfos = new HashMap<String, String>();

    /** 设置crash文件位置 **/
    private String mDRCrashFilePath;

    /** 生成的crash文件 **/
    private File crashFile;

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        // 1、上下文
        mContext = context;
        // 2、获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 3、初始化参数
//        initParams();
        // 4、设置当前CrashHandler为默认处理异常类
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (mDefaultHandler != null && !handlerException(ex)) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {

//            mContext.finishActivity();


//            ToastTop.INS.show();
//            Toast.makeText(mContext,"wwww",Toast.LENGTH_LONG).show();
//            System.exit(0);
        }
    }
    /**
     * 5、处理异常<br>
     * <br>
     *
     * 5.1 收集设备参数信息<br>
     * 5.2 弹出窗口提示信息<br>
     * 5.3 保存log和crash到文件<br>
     * 5.4 发送log和crash到服务器<br>
     *
     * @param ex
     * @return 是否处理了异常
     */
    protected boolean handlerException(Throwable ex) {

        if (ex == null) {
            return false;
        } else {

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
        ThrowableMsg throwableMsg = new ThrowableMsg(((InvocationTargetException)ex.getCause()).getTargetException());

//        Log.e(TAG, "saveLogAndCrash: "+ex.getMessage() );
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
