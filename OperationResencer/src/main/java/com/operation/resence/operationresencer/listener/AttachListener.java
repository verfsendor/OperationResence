package com.operation.resence.operationresencer.listener;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;

import com.operation.resence.operationresencer.utils.Constants;
import com.operation.resence.operationresencer.utils.HookHelper;
import com.operation.resence.operationresencer.utils.ViewHelper;

/**
 * Created by xuzhendong on 2018/9/11.
 * 当viewgroup中attach关系变化时触发，可以获取到新加入的fragment的view
 */

public class AttachListener implements View.OnAttachStateChangeListener {
    @Override
    public void onViewAttachedToWindow(View v) {
        Log.v("fragmenthook","window添加view啦 onViewAttachedToWindow");
        /**
         * 如果当前activity类型是FragmentActivity，通过hook获取具体fragment实例再遍历view，否则直接遍历view
         */
        if(Constants.currentActivity instanceof FragmentActivity){
            Log.v("fragmenthook","hookhookFragmentManager 1");
            HookHelper.hookFragmentManager((FragmentActivity)Constants.currentActivity);
        }else {
            Log.v("fragmenthook","hookhookFragmentManager ViewHelper.travelView");
            ViewHelper.travelView("",v);
        }
    }

    @Override
    public void onViewDetachedFromWindow(View v) {

    }
}
