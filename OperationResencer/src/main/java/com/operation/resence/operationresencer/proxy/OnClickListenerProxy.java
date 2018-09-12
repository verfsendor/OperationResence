package com.operation.resence.operationresencer.proxy;

import android.util.Log;
import android.view.View;

/**
 * Created by xuzhendong on 2018/9/12.
 */

//自定义的代理事件监听器
public class OnClickListenerProxy implements View.OnClickListener {

    private View.OnClickListener object;

    public OnClickListenerProxy(View.OnClickListener object) {
        this.object = object;
    }

    @Override
    public void onClick(View v) {
        Log.v("verf","点击被劫持");
        object.onClick(v);
    }
}