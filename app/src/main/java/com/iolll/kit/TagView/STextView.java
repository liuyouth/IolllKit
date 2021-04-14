package com.iolll.kit.TagView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.iolll.liubo.ifunction.IFunction;

/**
 * Created by LiuBo on 2021-04-10.
 */
public class STextView extends TextView {
    public STextView(Context context) {
        super(context);
        init();
    }

    public STextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public STextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public STextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    private void init(){
        setMaxLines(1);
    }

    public float getTextViewWidth(){
        return getPaint().measureText(getText().toString());
    }
    private IFunction.Run<Float> textWidthListenner;

    public void setTextWidthListenner(IFunction.Run<Float> textWidthListenner) {
        this.textWidthListenner = textWidthListenner;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (textWidthListenner!=null)
        textWidthListenner.run(getTextViewWidth());
    }
}
