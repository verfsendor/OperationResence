package com.operation.resence.operationresencer.proxy;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xuzhendong on 2018/9/11.
 */

public class OnKeyListenerProxy implements View.OnKeyListener {
    private View.OnKeyListener object;

    public OnKeyListenerProxy(View.OnKeyListener object) {
        this.object = object;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return object.onKey(v, keyCode, event);
    }
}
