package com.iolll.kit.TagView;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.iolll.kit.R;
import com.iolll.liubo.bottomrecyclerdialog.DensityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import kotlin.jvm.Volatile;

/**
 * Created by LiuBo on 2021-04-04.
 */
public class TagGraoup extends LinearLayout {
    public TagGraoup(Context context) {
        super(context);
        dwidth = DensityUtils.dp2px(getContext(), 1 + 10 + 10);
    }

    public TagGraoup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        dwidth = DensityUtils.dp2px(getContext(), 1 + 10 + 10);
    }

    public TagGraoup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        dwidth = DensityUtils.dp2px(getContext(), 1 + 10 + 10);
    }

    public TagGraoup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        dwidth = DensityUtils.dp2px(getContext(), 1 + 10 + 10);
        System.out.println("getw con");
    }

    private ArrayList<String> datas;
    private ArrayList<String> drawDatas;
    @Volatile
    private  AtomicBoolean isLayout = new AtomicBoolean(false);

    void setData(List<String> data) {
        System.out.println("getw data"+getWidth());
        datas = (ArrayList<String>) data;
        isLayout.set(false);
        System.out.println("getw data is"+isLayout+getWidth());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        /**
//         * Extracts the mode from the supplied measure specification
//         * 从提供的测量规范中获取模式
//         */
//        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
//        /**
//         * Extracts the size from the supplied measure specification
//         * 从提供的测量规范中获取大小
//         */
//        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
//
//        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
//        /**
//         * 如果宽度的测量模式是AT_MOST
//         * 则设置宽度
//         */
//        if(widthMode==MeasureSpec.AT_MOST){
//
//            widthSize= (int) (getPaddingLeft()+getPaddingRight());
//        }
//
//        if(heightMode==MeasureSpec.AT_MOST){
//
//            heightSize= (int) ( getPaddingTop()+getPaddingBottom());
//        }
//
//        setMeasuredDimension(widthSize,heightSize);
        panrentWidth = getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        panrentWidth = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        System.out.println("init  getw draw "+isLayout + getWidth());
        if (!isLayout.get()) {
            isLayout.set(true);
            initView();
        }else {
            System.out.println("init  getw draw "+(datas == drawDatas));
            // 只触发onDraw
            if (drawDatas!=datas){
                initView();
            }
        }
    }

    private int maxLines = 2;
    private int lines = 0;

    private float panrentWidth = 0;
    float remainingWidth = 0;
    int layoutJoinStvCount = 0;
    LinearLayout linearLayout;
    private ArrayList<LinearLayout> linears = new ArrayList<>();

    private void initView() {
        if (null == datas || datas.size() == 0)
            return;

        System.out.println("init  getw " + getWidth());
        drawDatas =datas;
        removeAllViews();
        layoutJoinStvCount = 0;
        lines = 0;

        remainingWidth = panrentWidth;
//        if (linears.size()>0)
//            linearLayout = linears.get(lines);
        linearLayout = getLinerLayout();
        for (int i = 0; i < datas.size(); i++) {
            String s = datas.get(i);
            if (lines > maxLines)
                return;
            LinearLayout le = addStv(s, linearLayout);
            if (datas.size() - 1 == i) {
                if (lines < maxLines) {
                        lines++;
                        addView(le);
                }
            }
        }
        System.out.println("init  getw lines" + lines);
    }

    private LinearLayout addStv(String s, LinearLayout linearLayout) {
        if (lines >= maxLines) {
            return linearLayout;
        }
        View line = null;
        if (layoutJoinStvCount != 0) {
            if (remainingWidth >= dwidth) {
                line = addLineView();
            } else {
                return addStv(s, addLinearLayoutViewAndRefresh(linearLayout));
            }
        }
        STextView stv = getTagView(s);
        float w = stv.getTextViewWidth();
        w += dwidth;
        if (remainingWidth >= w) {
            remainingWidth = remainingWidth - w;
            layoutJoinStvCount++;
            if (null != line) {
                linearLayout.addView(line);
            }
            linearLayout.addView(stv);
            // 完成循环 进行下一个
        } else {
            if (panrentWidth<=w){
                if (layoutJoinStvCount!=0){
                    return addStv(s, addLinearLayoutViewAndRefresh(linearLayout));
                }else {
                    linearLayout.addView(stv);
                    // 完成循环 进行下一个
                }
            }else {
                return addStv(s, addLinearLayoutViewAndRefresh(linearLayout));
            }
        }
        return linearLayout;
    }

    private LinearLayout addLinearLayoutViewAndRefresh(LinearLayout linearLayout) {
        if (lines < maxLines) {
            lines++;
            addView(linearLayout);
            remainingWidth = panrentWidth;
            layoutJoinStvCount = 0;
            this.linearLayout = getLinerLayout();
            return this.linearLayout;
        }
        return linearLayout;
    }

    public static float dwidth;
    /**
     * 分割线
     * 默认 宽度为 21dp
     * @return
     */
    private View addLineView() {
        View view = new View(getContext());
        view.setBackgroundColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        LinearLayout.LayoutParams params = (LayoutParams) view.getLayoutParams();
        if (params == null)
            params = new LayoutParams(DensityUtils.dp2px(getContext(), 1), DensityUtils.dp2px(getContext(), 10));
        params.weight = DensityUtils.dp2px(getContext(), 1);
        params.height = DensityUtils.dp2px(getContext(), 10);
        params.leftMargin = DensityUtils.dp2px(getContext(), 10);
        params.rightMargin = DensityUtils.dp2px(getContext(), 10);
        view.setLayoutParams(params);
        return view;
    }

    private STextView getTagView(String data) {
        STextView sTextView = new STextView(getContext());
        sTextView.setText(data);
        ViewGroup.MarginLayoutParams layoutParams = (MarginLayoutParams) sTextView.getLayoutParams();
//        if (layoutParams == null)
//            layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.bottomMargin = 100;
        sTextView.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        return sTextView;
    }


    /**
     * 设置每个横向的 layout属性
     *
     * @return
     */
    private LinearLayout getLinerLayout() {
        LinearLayout l = new LinearLayout(getContext());
        l.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = (LayoutParams) l.getLayoutParams();
        if (params == null)
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        l.setLayoutParams(params);
        l.setGravity(Gravity.CENTER_VERTICAL);
        params.setMargins(0,0,0,20);
        return l;
    }

}
