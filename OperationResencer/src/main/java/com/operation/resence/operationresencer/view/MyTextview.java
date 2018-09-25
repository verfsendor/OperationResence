package com.operation.resence.operationresencer.view;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.operation.resence.operationresencer.utils.OperationResencer;
import com.operation.resence.operationresencer.utils.Util;
/**
 * Created by xuzhendong on 2018/9/11.
 */
public class MyTextview extends android.support.v7.widget.AppCompatTextView {
    public MyTextview(Context context) {
        super(context);
    }

    public MyTextview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!OperationResencer.test) {
            Log.v("verf", "haveOnTouch eventTime " + event.getEventTime()  + " downTime  " + event.getDownTime() + " " + getTag() + " " + event.getRawX() + " " + event.getRawY()
                    + " " + Util.getActionTxt(event.getAction()) + " " + getText());
        }
        return super.onTouchEvent(event);
    }
}
