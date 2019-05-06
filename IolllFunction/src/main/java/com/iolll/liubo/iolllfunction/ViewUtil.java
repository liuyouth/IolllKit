package com.iolll.liubo.iolllfunction;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by LiuBo on 2019/4/11.
 */
public class ViewUtil {
    public static Drawable[] getDrawablesAndChange(TextView textView, Drawable drawable, int index) {
        Drawable[] drawables = textView.getCompoundDrawables();
        drawables[index] = drawable;
        return drawables;
    }
    /**
     * 批量控制view 隐藏显示
     *
     * @param visibility
     */
    public static void setVisibleOrGoneOfViews(@IInteface.Visibility int visibility, View... views) {
        for (View view : views) view.setVisibility(visibility);
//        forEach(view-> view.setVisibility(visibility),views);
    }
    public static void setDrawaleLeft(TextView titleTv, Drawable drawable) {
        Drawable[] drawables = getDrawablesAndChange(titleTv, drawable, 0);
        titleTv.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    public static void setDrawaleTop(TextView titleTv, Drawable drawable) {
        Drawable[] drawables = getDrawablesAndChange(titleTv, drawable, 1);
        titleTv.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    public static void setDrawaleRight(TextView titleTv, Drawable drawable) {
        Drawable[] drawables = getDrawablesAndChange(titleTv, drawable, 2);
        titleTv.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    public static void setDrawaleBottom(TextView titleTv, Drawable drawable) {
        Drawable[] drawables = getDrawablesAndChange(titleTv, drawable, 3);
        titleTv.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }
}
