package com.iolll.kit.vpFragments.base;

import androidx.lifecycle.LifecycleObserver;


/**
 * Created by LiuBo on 2018/12/13.
 */
public interface Presenter<V> extends LifecycleObserver,ActLife {
    void attachView(V v);

    void detachView();




}

