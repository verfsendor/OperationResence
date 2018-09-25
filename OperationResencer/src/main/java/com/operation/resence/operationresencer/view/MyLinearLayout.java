package com.operation.resence.operationresencer.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.operation.resence.operationresencer.utils.Util;

/**
 * Created by xuzhendong on 2018/9/18.
 */

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.v("verf","myLinearLayout dispatchTouchEvent " + Util.getActionTxt(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v("verf","myLinearLayout onTouchEvent " + Util.getActionTxt(event.getAction()));
        return super.onTouchEvent(event);
    }
}
