package com.operation.resence.operationresencer.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.operation.resence.operationresencer.utils.OperationResencer;
import com.operation.resence.operationresencer.utils.Util;

/**
 * Created by xuzhendong on 2018/9/13.
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!OperationResencer.test) {
            Log.v("verf", "haveOnTouch eventTime " + event.getEventTime()  + " downTime  " + event.getDownTime() + " " + getTag() + " " + event.getRawX() + " " + event.getRawY()
                    + " " + Util.getActionTxt(event.getAction()));
        }
        return super.onTouchEvent(event);
    }
}
