package com.operation.resence.operationresencer.proxy;

import android.util.Log;
import android.view.View;

import java.util.Calendar;

/**
 * Created by xuzhendong on 2018/9/12.
 */

//自定义的代理事件监听器
public class OnClickListenerProxy implements View.OnClickListener {
    private View.OnClickListener object;
    private int MIN_CLICK_DELAY_TIME = 500;

    private long lastClickTime = 0;
    public OnClickListenerProxy(View.OnClickListener object) {
        this.object = object;
    }

    @Override
    public void onClick(View v) {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            if(object != null){
                object.onClick(v);
            }
        }
    }
}