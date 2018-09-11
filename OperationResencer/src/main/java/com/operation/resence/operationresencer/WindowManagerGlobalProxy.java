package com.operation.resence.operationresencer;

import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.view.Window;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xuzhendong on 2018/9/7.
 */

public  class WindowManagerGlobalProxy  {

    WindowManagerGlobalProxy mBase;

    public WindowManagerGlobalProxy(WindowManagerGlobalProxy base) {
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
}

