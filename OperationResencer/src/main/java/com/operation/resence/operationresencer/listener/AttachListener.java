package com.operation.resence.operationresencer.listener;

import android.view.View;

import com.operation.resence.operationresencer.utils.ViewHelper;

/**
 * Created by xuzhendong on 2018/9/11.
 * 当viewgroup中attach关系变化时触发，可以获取到新加入的fragment的view
 */

public class AttachListener implements View.OnAttachStateChangeListener {
    @Override
    public void onViewAttachedToWindow(View v) {
        ViewHelper.travelView("",v);
    }

    @Override
    public void onViewDetachedFromWindow(View v) {

    }
}
