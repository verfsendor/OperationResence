package com.operation.resence.operationresencer.proxy;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.operation.resence.operationresencer.bean.TouchEventBean;
import com.operation.resence.operationresencer.utils.TestManager;

/**
 * Created by xuzhendong on 2018/9/11.
 */

public class OnTouchListenerProxy implements View.OnTouchListener {
    private View.OnTouchListener object;

    public OnTouchListenerProxy(View.OnTouchListener object) {
        this.object = object;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.v("verf","hook view ontouch ");
        if(TestManager.test){
            TouchEventBean eventBean = new TouchEventBean();
            eventBean.setRawX(event.getRawX());
            eventBean.setRawY(event.getRawY());
            eventBean.setTime(event.getEventTime());
            eventBean.setAction(event.getAction());
            eventBean.setPageName("" + v.getTag());
            TestManager.addEvent(eventBean);
        }
        return object.onTouch(v,event);
    }
}
