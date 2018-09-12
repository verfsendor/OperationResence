package com.operation.resence.operationresencer;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.operation.resence.operationresencer.bean.TouchEventBean;
import com.operation.resence.operationresencer.utils.TestManager;
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
        TouchEventBean eventBean = new TouchEventBean();
        eventBean.setRawX(event.getRawX());
        eventBean.setRawY(event.getRawY());
        eventBean.setTime(System.currentTimeMillis());
        eventBean.setAction(event.getAction());
        eventBean.setPageName("" + getTag());
        TestManager.addEvent(eventBean);
        Log.v("verf",getTag() + " MytextView " + getText() + " " + event.getRawX()  + " " + event.getRawY() + " " + Util.getActionTxt(event.getAction()));
        return super.onTouchEvent(event);
    }
}
