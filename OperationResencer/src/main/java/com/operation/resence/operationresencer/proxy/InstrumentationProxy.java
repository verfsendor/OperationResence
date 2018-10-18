package com.operation.resence.operationresencer.proxy;
import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.operation.resence.operationresencer.man;
import com.operation.resence.operationresencer.ssdd;
import com.operation.resence.operationresencer.utils.Constants;
import com.operation.resence.operationresencer.utils.HookHelper;
import com.operation.resence.operationresencer.utils.ViewHelper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by xuzhendong on 2018/9/7.
 * hook ActivityThread 中的Instrumentation对象，劫持activity生命周期
 */
public  class InstrumentationProxy extends Instrumentation {
    // ActivityThread中原始的Instrumentation对象, 保存起来
    Instrumentation mBase;
    public InstrumentationProxy(Instrumentation base) {
        this.mBase = base;
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        super.callActivityOnCreate(activity, icicle);
        Log.v("fragmenthook","callActivityOnCreate ");
        try {
            Constants.currentActivity = activity;
            Constants.focusWindowActivityName = activity.getClass().getSimpleName();
            //记录当前正在显示的activity名称
            ViewHelper.travelView(activity.getClass().getSimpleName(),activity.getWindow().getDecorView());
            mBase.callActivityOnCreate(activity,icicle);
        }catch (Exception e){
           e.printStackTrace();
        }
    }

    /**
     * 保存当前正在显示的activity
     * @param activity
     */
    public void callActivityOnResume(Activity activity) {
        try {

            Log.v("fragmenthook","callActivityOnResume ");
            activity.getWindow().setCallback(new WindowCallbackProxy(activity, activity.getWindow().getCallback()));
            mBase.callActivityOnResume(activity);
//            man man = new man();
//            InvocationHandler handler = new WindowProxyHandler(man);
//            ssdd ssdd = (com.operation.resence.operationresencer.ssdd) Proxy.newProxyInstance(man.getClass().getClassLoader(),
//                    man.getClass().getInterfaces(), handler);
//            ssdd.lista();
//            Window.Callback callback = (Window.Callback)WindowProxyHandler.newProxyInstance(activity.getWindow().getCallback());
//            Log.v("fragmenthook","WindowProxyHandler2 " + ssdd.getClass().getSimpleName());
//            callback.onWindowFocusChanged(false);
//            HookHelper.hookWindowManagerGlobal();
//            activity.getWindow().setCallback(new WindowCallbackProxy(activity, activity.getWindow().getCallback()));
//            ViewHelper.travelView(activity.getClass().getSimpleName(),activity.getWindow().getDecorView());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

