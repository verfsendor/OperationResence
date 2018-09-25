package com.operation.resence.operationresencer.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.diy.charts.view.SlikLineChart;
import com.operation.resence.operationresencer.utils.OperationResencer;
import com.operation.resence.operationresencer.utils.Util;

/**
 * Created by xuzhendong on 2018/9/13.
 */

public class Mychart extends SlikLineChart {
    public Mychart(Context context) {
        super(context);
    }

    public Mychart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Mychart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(OperationResencer.test) {
            Log.v("lalala", "chart click " + event.getAction() + "  " + event.getRawX() + " " + event.getRawY() + " " + Util.getActionTxt(event.getAction())
                    + " PointerCount = " + event.getPointerCount());
        }
        if(!OperationResencer.test) {
            Log.v("lalala", "chart 接到事件 "  + event.getAction() + "  "+ event.getRawX() + " " + event.getRawY() + " " + Util.getActionTxt(event.getAction())
                    + " PointerCount = " + event.getPointerCount());
        }
        return super.onTouchEvent(event);
    }
}
