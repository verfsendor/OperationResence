package com.operation.resence.operationresencer.proxy;
import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;

import com.operation.resence.operationresencer.utils.Constants;

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

    /**
     * 保存当前正在显示的activity
     * @param activity
     */
    public void callActivityOnResume(Activity activity) {
        try {
            //记录当前正在显示的activity名称
            Constants.nowActivityName = activity;
            mBase.callActivityOnResume(activity);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

