package com.operation.resence.operationresence;

import android.app.Application;

import com.operation.resence.operationresencer.HookHelper;

/**
 * Created by xuzhendong on 2018/9/10.
 */

public class MyApplicaiton extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            HookHelper.replaceInstrumentation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
