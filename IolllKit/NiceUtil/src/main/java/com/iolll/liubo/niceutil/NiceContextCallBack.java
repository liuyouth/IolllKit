package com.iolll.liubo.niceutil;

import com.google.gson.Gson;

/**
 * Created by LiuBo on 2019/4/11.
 */
public interface NiceContextCallBack {
    Gson gson();
    void toast(String s);

    void toast(int s);

    void longToast(String s);

    void longToast(int s);
}
