package com.liubo.allforoneiolllkit.iolllviewx;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.iolll.liubo.ifunction.IFunction;
import com.liubo.allforoneiolllkit.iolllfunction.IInteface;

import static com.iolll.liubo.niceutil.ValueUtils.isEmpty;

/**
 * Created by LiuBo on 2019-05-05.
 */
public class ViewUtils {
        /**
     * 根据文本自动隐藏本view
     *
     * @param tv
     * @param str
     */
    public static void setTextAutoVisibility(TextView tv, String str) {
        setTextAutoVisibility(tv, tv, str,null);
    }
    public static void setTextAutoVisibility(TextView tv, String str, IFunction.RunR<String,String> r) {
        setTextAutoVisibility(tv, tv, str,r);
    }
    public static void setTextAutoVisibility(TextView tv, View v, String str){
        setTextAutoVisibility(tv, v, str,null);
    }
    /**
     * 根据文本自动隐藏 指定view
     *
     * @param tv
     * @param str
     */
    public static void setTextAutoVisibility(TextView tv, View v, String str,IFunction.RunR<String,String> r) {
        boolean isEmpty = isEmpty(str);
        v.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        if (!isEmpty) tv.setText(r==null? str : r.run(str));
    }

        /**
     * 只要有一个 Visible  就返回 Visible
     *
     * @param runR
     * @param views
     * @param <T>
     * @return
     */
    public static <T> int getAllViewVisible(IFunction.RunR<T, Boolean> runR, T... views) {
        int visible = View.GONE;
//        forEachCheck(runR,Arrays.asList(views));
        if (null == views)
            return visible;
        for (T t : views) {
            if (runR.run(t)) {
                visible = View.VISIBLE;
            }
        }

        return visible;
    }

    public static Drawable[] getDrawablesAndChange(TextView textView, Drawable drawable, int index) {
        Drawable[] drawables = textView.getCompoundDrawables();
        drawables[index] = drawable;
        return drawables;
    }

    public static void setDrawaleLeft(TextView titleTv, Drawable drawable) {
        Drawable[] drawables = getDrawablesAndChange(titleTv, drawable, 0);
        titleTv.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    public static void setDrawaleTop(TextView titleTv, Drawable drawable) {
        Drawable[] drawables = getDrawablesAndChange(titleTv, drawable, 1);
        titleTv.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    public static void setDrawaleRight(TextView titleTv, Drawable drawable) {
        Drawable[] drawables = getDrawablesAndChange(titleTv, drawable, 2);
        titleTv.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    public static void setDrawaleBottom(TextView titleTv, Drawable drawable) {
        Drawable[] drawables = getDrawablesAndChange(titleTv, drawable, 3);
        titleTv.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    /**
     * 控制上车地点、下车地点、乘车人等项的显示
     *
     * @param visibility
     */
    public static void setVisibleOrGoneOfViews(@IInteface.Visibility int visibility, View... views) {
        for (View view : views) if (view!=null) view.setVisibility(visibility);
//        forEach(view-> view.setVisibility(visibility),views);
    }
}
