package com.operation.resence.operationresencer.proxy;
import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.view.Window;

import com.operation.resence.operationresencer.HookHelper;
import com.operation.resence.operationresencer.HookTouchEvent;
import com.operation.resence.operationresencer.ViewManager;
import com.operation.resence.operationresencer.utils.Constants;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * Created by xuzhendong on 2018/9/7.
 */

public  class InstrumentationProxy extends Instrumentation {
    // ActivityThread中原始的Instrumentation对象, 保存起来
    Instrumentation mBase;
    public InstrumentationProxy(Instrumentation base) {
        mBase = base;
    }


    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        try {
            if(activity.getWindow() != null) {
                Window window = activity.getWindow();
                window.setCallback(new HookTouchEvent(window.getCallback()));
                Method callActivityOnCreate = Instrumentation.class.getDeclaredMethod("callActivityOnCreate", Activity.class, Bundle.class);
                callActivityOnCreate.setAccessible(true);
                callActivityOnCreate.invoke(mBase, activity, icicle);
            }
            if(activity.getWindow() != null && activity.getWindow().getDecorView() != null){
                ViewManager.getEvent(activity.getClass().getSimpleName(), activity.getWindow().getDecorView());
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

    /**
     * 只有activity进入stop状态（hook 不到onfinsh())，如果nowActivty持有该对象则立即置空，避免造成内存泄漏
     * @param activity
     */
    public void callActivityOnStop(Activity activity) {
        try {
            //记录当前正在显示的activity名称
            if(Constants.nowActivityName != null &&
                    activity.getClass().getSimpleName().equals(Constants.nowActivityName.getClass().getSimpleName())) {
                Constants.nowActivityName = null;
            }
            if(activity.getWindow() != null) {
                Method callActivityOnStop = Instrumentation.class.getDeclaredMethod("callActivityOnStop", Activity.class);
                callActivityOnStop.setAccessible(true);
                callActivityOnStop.invoke(mBase, activity);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

