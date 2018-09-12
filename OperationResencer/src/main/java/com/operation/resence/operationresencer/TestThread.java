package com.operation.resence.operationresencer;
import android.app.Instrumentation;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import com.operation.resence.operationresencer.bean.BaseEvent;
import com.operation.resence.operationresencer.bean.KeyEventBean;
import com.operation.resence.operationresencer.bean.TouchEventBean;
import com.operation.resence.operationresencer.utils.Constants;
import com.operation.resence.operationresencer.utils.TestManager;
import static com.operation.resence.operationresencer.utils.TestManager.events;
import static com.operation.resence.operationresencer.utils.TestManager.i;
/**
 * Created by xuzhendong on 2018/9/11.
 */

public class TestThread extends Thread {
    private static Instrumentation mInst = new Instrumentation();
    private static String EVENT = "event";

    private static android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.v("verf","handleMessage " );
            BaseEvent event = (BaseEvent) msg.getData().getSerializable(EVENT);
            if(event instanceof TouchEventBean){
                mInst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, ((TouchEventBean) event).getRawX(), ((TouchEventBean) event).getRawY(), 0));    //x,y 即是事件的坐标
            }
            if(event instanceof KeyEventBean){
                mInst.sendKeyDownUpSync(((KeyEventBean) event).getKeyCode());
            }
        }
    };
    @Override
    public void run() {
        TestManager.test = false;
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i = 0;
        while (TestManager.events != null && TestManager.events.size() > i){
            long delay = 0;
            if( i - 1 > 0){
                delay = TestManager.events.get(i).getTime() - TestManager.events.get(i - 1).getTime();
            }

            Log.v("time","获取时间差" + delay);
            String[] path = events.get(i).getPageName().split("#");
            try {
                while (!path[0].equals(Constants.nowActivityName.getClass().getSimpleName())){
                    sleep(100);
                }
                sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BaseEvent event = events.get(i);
            if(event instanceof TouchEventBean){
                ViewManager.setEventToView(Constants.nowActivityName.getWindow().getDecorView(),MotionEvent.obtain(SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(), ((TouchEventBean) event).getAction(), ((TouchEventBean) event).getRawX(), ((TouchEventBean) event).getRawY(), 0),path[1],"");
//                mInst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
//                        SystemClock.uptimeMillis(), ((TouchEventBean) event).getAction(), ((TouchEventBean) event).getRawX(), ((TouchEventBean) event).getRawY(), 0));    //x,y 即是事件的坐标
            }
            if(event instanceof KeyEventBean){
                mInst.sendKeyDownUpSync(((KeyEventBean) event).getKeyCode());
            }
            i ++;
        }
        TestManager.events.clear();
    }
}
