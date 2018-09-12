package com.operation.resence.operationresencer;
import android.app.Instrumentation;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import com.operation.resence.operationresencer.bean.BaseEvent;
import com.operation.resence.operationresencer.bean.EditTextEventBean;
import com.operation.resence.operationresencer.bean.TouchEventBean;
import com.operation.resence.operationresencer.utils.Constants;
import com.operation.resence.operationresencer.utils.TestManager;
import static com.operation.resence.operationresencer.utils.TestManager.events;
/**
 * Created by xuzhendong on 2018/9/11.
 */

public class TestThread extends Thread {
    private int i;

    @Override
    public void run() {
        //关闭记录阀门，复现过程事件不会被记录
        TestManager.test = false;
        //休眠1秒，再开始复现过程
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
            String[] path = events.get(i).getPageName().split("#");
            try {
                //如果当前事件所属的activity与此时正在显示的activity不同，那么休眠0.1秒等待
                while (!path[0].equals(Constants.nowActivityName.getClass().getSimpleName())){
                    sleep(100);
                }
                sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BaseEvent event = events.get(i);
            if(event instanceof TouchEventBean){
                //派发事件
                Log.v("verf","派发事件 " + i + " " + event.getPageName());
                ViewManager.setTouchEventToView(Constants.nowActivityName.getWindow().getDecorView(),MotionEvent.obtain(SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(), ((TouchEventBean) event).getAction(), ((TouchEventBean) event).getRawX(), ((TouchEventBean) event).getRawY(), 0),path[1],"");
            }
            if(event instanceof EditTextEventBean){
                ViewManager.setTextEventToView(Constants.nowActivityName.getWindow().getDecorView(),((EditTextEventBean) event).getTxt(),path[1],"");
            }
            i ++;
        }
        //执行结束后清空记录的事件
        TestManager.events.clear();
    }
}
