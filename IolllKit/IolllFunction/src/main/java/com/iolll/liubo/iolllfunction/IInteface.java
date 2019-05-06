package com.iolll.liubo.iolllfunction;

import androidx.annotation.IntDef;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by LiuBo on 2018/12/17.
 */
public class IInteface {
    /**
     * @hide
     */
    @IntDef({View.VISIBLE, View.INVISIBLE, View.GONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }

}
