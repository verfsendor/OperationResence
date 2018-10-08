package com.operation.resence.operationresencer.utils;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;

import com.operation.resence.operationresencer.bean.BaseEventBean;
import com.operation.resence.operationresencer.bean.EditTextEventBean;
import com.operation.resence.operationresencer.bean.TouchEventBean;
import com.operation.resence.operationresencer.OperationResencer;

import java.util.ArrayList;

/**
 * Created by xuzhendong on 2018/9/11.
 */

public class ResenceThread extends Thread {
    private int i;
    private long downtime;
    private ArrayList<BaseEventBean> beans = new ArrayList<>();
    public ResenceThread(ArrayList<BaseEventBean> eventBeans){
        this.beans = eventBeans;
    }

    @Override
    public void run() {
        //关闭记录阀门，复现过程事件不会被记录
        OperationResencer.recording = false;
        i = 0;
        //休眠1秒，再开始复现过程
        sleepMills(1000);
        while (beans != null && beans.size() > i){
            long delay = i == 0 ? 0 : beans.get(i).getTime() - beans.get(i - 1).getTime();
            /**
             * path分割字符串 "#"前面部分为view所属界面的类名，如果与当前显示的界面不同，就休眠线程进行等待
             * */
            String[] path = beans.get(i).getPageName().split("#");
            if(path.length == 2) {
                if (!path[0].equals(Constants.nowActivityName.getClass().getSimpleName())) {
                    resenceStock();
                }
                //如果当前事件所属的activity与此时正在显示的activity不同，那么休眠0.1秒等待
                while (!path[0].equals(Constants.nowActivityName.getClass().getSimpleName())) {
                    sleepMills(100);
                }
                sleepMills(delay);
                BaseEventBean event = beans.get(i);
                if (event instanceof TouchEventBean) {
                    if (((TouchEventBean) event).getAction() == MotionEvent.ACTION_DOWN) {
                        downtime = SystemClock.uptimeMillis();
                    }
                    MotionEvent motionEvent = ((TouchEventBean) event).toMotionEvent(downtime == 0 ? SystemClock.uptimeMillis() : downtime);
                    ViewHelper.handleTouchEventToView(Constants.nowActivityName.getWindow().getDecorView(), motionEvent, path[0], path[1], "");
                    if (((TouchEventBean) event).getAction() == MotionEvent.ACTION_UP) {
                        downtime = 0;
                    }
                }
                if (event instanceof EditTextEventBean) {
                    ViewHelper.handleTextEventToView(Constants.nowActivityName.getWindow().getDecorView(), ((EditTextEventBean) event).getTxt(), path[1], "");
                }
            }
            i ++;
        }
        //执行结束后清空记录的事件
        OperationResencer.resenceFinish();
        resenceFinish();
    }

    public void sleepMills(long mills){
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resenceStock(){
        Log.v("verf", "****need**" + beans.get(i).getPageName() + "***********复现阻断**************now**" + Constants.nowActivityName.getClass().getSimpleName() + "***");
    }

    public void resenceFinish(){
        Log.v("verf","*******************************复现结束**************");
    }

}
