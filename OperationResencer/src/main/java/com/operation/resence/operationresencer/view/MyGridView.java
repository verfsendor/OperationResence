package com.operation.resence.operationresencer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.GridView;

import com.operation.resence.operationresencer.utils.OperationResencer;
import com.operation.resence.operationresencer.utils.Util;

/**
 * Created by xuzhendong on 2018/9/14.
 */

public class MyGridView extends GridView {
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(!OperationResencer.isRecording())
        Log.v("verf","myGridview getEvent " + ev.getRawX() + " " + ev.getRawY() + " " + Util.getActionTxt(ev.getAction()));
        return super.onTouchEvent(ev);
    }
}
