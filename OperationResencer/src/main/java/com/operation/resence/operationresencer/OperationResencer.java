package com.operation.resence.operationresencer;

import android.util.Log;

import com.operation.resence.operationresencer.bean.BaseEventBean;
import com.operation.resence.operationresencer.bean.TouchEventBean;
import com.operation.resence.operationresencer.utils.Constants;
import com.operation.resence.operationresencer.utils.ResenceThread;
import com.operation.resence.operationresencer.utils.ViewHelper;

import java.util.ArrayList;

/**
 * Created by xuzhendong on 2018/9/11.
 */

public class OperationResencer {
    public static boolean recording = true; //判断记录是否开启，为true时才会记录点击值
    private static ArrayList<BaseEventBean> events = new ArrayList<>();//记录点击过程的事件

    public static void addEvent(BaseEventBean event){
        if(event instanceof TouchEventBean) {
            Log.v("verf", "add " + event.getPageName() + " " + ((TouchEventBean) event).getRawX() + " "  + ((TouchEventBean) event).getRawY() + " " + ((TouchEventBean) event).getActionTxt() + " " + events.size() );
        }
        if(events.size() <= Constants.MAX_RECORDS) {
            events.add(event);
        }
    }

    public static boolean isRecording() {
        return recording;
    }

    /**
     * 开始复现
     */
    public static void startResence(){
       new ResenceThread(events).start();
    }

    public static void resenceFinish(){
        events.clear();
        ViewHelper.recycle();
    }
}
