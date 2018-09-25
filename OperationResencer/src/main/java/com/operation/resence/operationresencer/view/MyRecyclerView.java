package com.operation.resence.operationresencer.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by xuzhendong on 2018/9/14.
 */

public class MyRecyclerView extends RecyclerView {
    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
//        Log.v("verf","MyRecyclerView onTouchEvent " + Util.getActionTxt(e.getAction()) + "  " + e.getRawX() + " " + e.getRawY() + " index = " + e.getPointerCount());
        return super.onTouchEvent(e);
    }
}
