package com.operation.resence.operationresence.utils;

import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by xuzhendong on 2018/9/19.
 */

public class WindowManagerProxy implements WindowManager {
    WindowManager windowManager;

    public WindowManagerProxy(WindowManager windowManager){
        this.windowManager = windowManager;
    }

    @Override
    public Display getDefaultDisplay() {
        return windowManager.getDefaultDisplay();
    }

    @Override
    public void removeViewImmediate(View view) {
        windowManager.removeViewImmediate(view);
    }

    @Override
    public void addView(View view, ViewGroup.LayoutParams params) {
       windowManager.addView(view,params);
    }

    @Override
    public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
       windowManager.updateViewLayout(view,params);
    }

    @Override
    public void removeView(View view) {
       windowManager.removeView(view);
    }
}
