package com.operation.resence.operationresencer.proxy;
import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.operation.resence.operationresencer.HookHelper;
import com.operation.resence.operationresencer.HookTouchEvent;
import com.operation.resence.operationresencer.ViewManager;
import com.operation.resence.operationresencer.utils.Constants;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * Created by xuzhendong on 2018/9/7.
 * hook ActivityThread 中的Instrumentation对象，劫持activity生命周期
 */
public  class InstrumentationProxy extends Instrumentation {
    // ActivityThread中原始的Instrumentation对象, 保存起来
    Instrumentation mBase;
    public InstrumentationProxy(Instrumentation base) {
        mBase = base;
    }


    /**
     * callActivityOnCreate
     * @param activity
     * @param icicle
     */
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        try {
            if(activity.getWindow() != null) {
                Window window = activity.getWindow();
                window.setCallback(new WindowCallbackProxy(activity,window.getCallback()));
                Method callActivityOnCreate = Instrumentation.class.getDeclaredMethod("callActivityOnCreate", Activity.class, Bundle.class);
                callActivityOnCreate.setAccessible(true);
                callActivityOnCreate.invoke(mBase, activity, icicle);
            }
            //遍历当前activity中的view，为view添加tag
            if(activity.getWindow() != null && activity.getWindow().getDecorView() != null){
                ViewManager.travelView(activity.getClass().getSimpleName(), activity.getWindow().getDecorView());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存当前正在显示的activity
     * @param activity
     */
    public void callActivityOnResume(Activity activity) {
        try {
            //记录当前正在显示的activity名称
            Constants.nowActivityName = activity;
            Log.v("verf","callActivityOnResume " + Constants.nowActivityName.getClass().getSimpleName());
            if(activity.getWindow() != null) {
                Method callActivityOnResume = Instrumentation.class.getDeclaredMethod("callActivityOnResume", Activity.class);
                callActivityOnResume.setAccessible(true);
                callActivityOnResume.invoke(mBase, activity);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 如果nowActivty持有该activity对象，只要activity进入了Destroy状态（Instrumentation 找不到callActivityonfinsh()方法)，就立即置空，避免造成内存泄漏
//     * @param activity
//     */
//    public void callActivityOnDestroy(Activity activity) {
//        try {
//            Log.v("verf","callActivityOnDestroy " + Constants.nowActivityName.getClass().getSimpleName());
//            //记录当前正在显示的activity名称
//            if(Constants.nowActivityName != null &&
//                    activity.getClass().getSimpleName().equals(Constants.nowActivityName.getClass().getSimpleName())) {
//                Constants.nowActivityName = null;
//            }
//            if(activity.getWindow() != null) {
//                Method callActivityOnDestroy = Instrumentation.class.getDeclaredMethod("callActivityOnDestroy", Activity.class);
//                callActivityOnDestroy.setAccessible(true);
//                callActivityOnDestroy.invoke(mBase, activity);
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//    }
}

