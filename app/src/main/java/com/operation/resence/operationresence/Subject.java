package com.operation.resence.operationresence;

import android.app.ActionBar;
import android.util.Log;
import android.view.View;

/**
 * Created by xuzhendong on 2018/9/19.
 */

public class Subject implements MyInterface {
    @Override
    public void getName(String name) {
        Log.v("zzzz","getName " + name);
    }

    @Override
    public void showName(String name) {
        Log.v("zzzz","showName " + name);
    }

    @Override
    public void addView(ActionBar.LayoutParams params, View view, String name) {
        Log.v("zzzz","addViewinginging " + name);
    }
}
