package com.operation.resence.operationresencer.utils;

import android.app.Instrumentation;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

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

public class TestManager {
    public static boolean test = true;
    public static int i = 0;
    public static ArrayList<BaseEvent> events = new ArrayList<>();
    public static long lasttime = -1;

    public static void addEvent(BaseEvent event){
        if(lasttime > 0) {
            lasttime = event.getTime();
        }
        if(events.size() < 500) {
            events.add(event);
        }
    }
    public static void startTest(){
       new TestThread().start();
    }
}
