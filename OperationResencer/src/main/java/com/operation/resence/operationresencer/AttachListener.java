package com.operation.resence.operationresencer;

import android.util.Log;
import android.view.View;

/**
 * Created by xuzhendong on 2018/9/11.
 */

public class AttachListener implements View.OnAttachStateChangeListener {
    @Override
    public void onViewAttachedToWindow(View v) {
        ViewManager.getEvent("",v);
    }

    @Override
    public void onViewDetachedFromWindow(View v) {

    }
}
