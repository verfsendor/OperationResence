package com.operation.resence.operationresencer.utils;

import android.app.Instrumentation;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.operation.resence.operationresencer.R;
import com.operation.resence.operationresencer.TestThread;
import com.operation.resence.operationresencer.bean.BaseEvent;
import com.operation.resence.operationresencer.bean.KeyEventBean;
import com.operation.resence.operationresencer.bean.TouchEventBean;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;

/**
 * Created by xuzhendong on 2018/9/11.
 */

public class OperationResencer {
    public static boolean test = true; //判断记录是否开启，为true时才会记录点击值
    public static ArrayList<BaseEvent> events = new ArrayList<>();//记录点击过程的事件

    public static void addEvent(BaseEvent event){
        if(event instanceof TouchEventBean) {
            Log.v("verf", "add " + event.getPageName() + " " + ((TouchEventBean) event).getRawX() + " "  + ((TouchEventBean) event).getRawY() + " " + ((TouchEventBean) event).getActionTxt() + " " + events.size() );
        }
        if(events.size() < 500) {
            events.add(event);
        }
    }

    public static boolean isRecording() {
        return test;
    }

    public static void startTest(){
       new TestThread().start();
    }
}
