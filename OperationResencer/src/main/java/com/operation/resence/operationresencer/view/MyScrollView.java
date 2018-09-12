package com.operation.resence.operationresencer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.operation.resence.operationresencer.bean.TouchEventBean;
import com.operation.resence.operationresencer.utils.TestManager;
import com.operation.resence.operationresencer.utils.Util;

/**
 * Created by xuzhendong on 2018/9/11.
 */

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        TouchEventBean eventBean = new TouchEventBean();
        eventBean.setRawX(ev.getRawX());
        eventBean.setRawY(ev.getRawY());
        eventBean.setTime(System.currentTimeMillis());
        eventBean.setAction(ev.getAction());
        eventBean.setPageName("" + getTag());
        TestManager.addEvent(eventBean);
        Log.v("verf",getTag() + " MyScrollView " + ev.getRawX()  + " " + ev.getRawY() + " " + Util.getActionTxt(ev.getAction()));
        return super.onTouchEvent(ev);
    }
}
