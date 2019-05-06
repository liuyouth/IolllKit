package com.iolll.liubo.iolllfunction;

import android.os.SystemClock;
import android.view.View;

/**
 * Created by LiuBo on 2018/11/28.
 */
public class ClickListener implements View.OnClickListener {
    MultiClickListener multiClickListener;
    OneClickListener oneClickListener;
    /**
     * 最大点击次数
     * 请勿设为1 设置为1时 onOneClick将失去拦截重复点击特性
     */
    private static int MAXCOUNTS = 2;
    /**
     * 规定有效时间
     */
    private static long DURATION = 300;
    private long[] mHits = new long[MAXCOUNTS];

    public ClickListener(MultiClickListener listener) {
        this.multiClickListener = listener;
    }

    public ClickListener(OneClickListener listener) {
        this.oneClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        //实现左移，然后最后一个位置更新距离开机的时间，如果最后一个时间和最开始时间小于DURATION，即连续N次点击
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        for (int i = 0; i < mHits.length; i++) {
            if (mHits[i] >= (SystemClock.uptimeMillis() - DURATION)) {
                //, MAXCOUNTS - i
                if (multiClickListener != null) multiClickListener.onClick(v, MAXCOUNTS - i);
                if (oneClickListener != null && MAXCOUNTS - i == 1) oneClickListener.onClick(v);
                if (i == 0)
                    mHits = new long[MAXCOUNTS];
                break;
            }
        }
    }

    public interface MultiClickListener {
        void onClick(View v, int count);
    }

    public interface OneClickListener {
        void onClick(View v);
    }

}
