package com.operation.resence.operationresence;

import android.app.ActionBar;
import android.view.View;

/**
 * Created by xuzhendong on 2018/9/19.
 */

public interface MyInterface {
    public void getName(String name);
    public void showName(String name);
    public void addView(ActionBar.LayoutParams params , View view, String name);
}
