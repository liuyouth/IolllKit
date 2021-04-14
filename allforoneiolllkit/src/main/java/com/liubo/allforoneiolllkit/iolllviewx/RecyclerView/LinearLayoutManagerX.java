package com.liubo.allforoneiolllkit.iolllviewx.RecyclerView;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Android X
 *
 * 在recyclerview 进行notifyDataChange的时候 进行数据的修改就会触发一个异常导致崩溃
 * 使用该LinearLayoutManager 就不会导致异常，
 * Created by LiuBo on 2019/3/4.
 */
public class LinearLayoutManagerX extends LinearLayoutManager {
    public LinearLayoutManagerX(Context context) {
        super( context );
    }

    public LinearLayoutManagerX(Context context, int orientation, boolean reverseLayout) {
        super( context, orientation, reverseLayout );
    }

    public LinearLayoutManagerX(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super( context, attrs, defStyleAttr, defStyleRes );
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            //这里捕获之前的数组越界问题...
            //只要你数据改变之后又进行了notify就不会有问题
            super.onLayoutChildren( recycler, state );
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }
}