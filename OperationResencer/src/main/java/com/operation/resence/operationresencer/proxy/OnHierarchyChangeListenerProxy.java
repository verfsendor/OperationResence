package com.operation.resence.operationresencer.proxy;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.operation.resence.operationresencer.utils.Constants;
import com.operation.resence.operationresencer.utils.HookHelper;
import com.operation.resence.operationresencer.utils.ViewHelper;

public class OnHierarchyChangeListenerProxy implements ViewGroup.OnHierarchyChangeListener {
    ViewGroup.OnHierarchyChangeListener listener;
    public OnHierarchyChangeListenerProxy(ViewGroup.OnHierarchyChangeListener listener){
        this.listener = listener;
    }
    @Override
    public void onChildViewAdded(View parent, View child) {
        Log.v("fragmenthook","group 添加view啦 onChildViewAdded " + child);
        /**
         * 如果当前activity类型是FragmentActivity，通过hook获取具体fragment实例再遍历view，否则直接遍历view
         */
        if(Constants.currentActivity instanceof FragmentActivity){
            HookHelper.hookFragmentManager((FragmentActivity)Constants.currentActivity);
        }else {
            ViewHelper.travelView("",child);
        }
        if(listener != null) {
            listener.onChildViewAdded(parent, child);
        }

    }

    @Override
    public void onChildViewRemoved(View parent, View child) {
        if(listener != null) {
            listener.onChildViewRemoved(parent, child);
        }
    }

}
