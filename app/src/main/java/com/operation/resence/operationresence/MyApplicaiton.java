package com.operation.resence.operationresence;

import android.app.Application;
import android.util.Log;

import com.operation.resence.operationresencer.HookHelper;
import com.operation.resence.operationresencer.utils.TestManager;

/**
 * Created by xuzhendong on 2018/9/10.
 */

public class MyApplicaiton extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            HookHelper.replaceInstrumentation();
            Log.v("verf"," TestManager size " + TestManager.events.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
