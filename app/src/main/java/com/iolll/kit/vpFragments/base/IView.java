package com.iolll.kit.vpFragments.base;

import android.content.Context;

/**
 * Created by LiuBo on 2018/12/13.
 */
public interface IView {
    void showToast(String msg);
    void showToast(int msgId);
    Context getContext();

}
