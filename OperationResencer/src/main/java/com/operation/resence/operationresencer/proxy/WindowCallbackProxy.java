package com.operation.resence.operationresencer.proxy;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

import com.operation.resence.operationresencer.HookHelper;
import com.operation.resence.operationresencer.utils.Constants;

public class WindowCallbackProxy implements Window.Callback {
    final Window.Callback callback;
    final Activity activity;

    public WindowCallbackProxy(Activity activity,Window.Callback callback){
        this.callback = callback;
        this.activity = activity;
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return callback.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return callback.dispatchKeyShortcutEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return callback.dispatchTouchEvent(event);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent event) {
        return callback.dispatchTrackballEvent(event);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        return callback.dispatchGenericMotionEvent(event);
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return callback.dispatchPopulateAccessibilityEvent(event);
    }

    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
        return callback.onCreatePanelView(featureId);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return callback.onCreatePanelMenu(featureId,menu);
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        return callback.onPreparePanel(featureId,view,menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return callback.onMenuOpened(featureId,menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return callback.onMenuItemSelected(featureId,item);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams attrs) {
        callback.onWindowAttributesChanged(attrs);
    }

    @Override
    public void onContentChanged() {
       callback.onContentChanged();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        HookHelper.hookWindowManagerGlobal(activity);
        Log.v("verf",Constants.nowActivityName.getClass().getSimpleName() + " onWindowFocusChanged " + hasFocus );
        callback.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onAttachedToWindow() {
      callback.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
      callback.onDetachedFromWindow();
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
      callback.onPanelClosed(featureId, menu);
    }

    @Override
    public boolean onSearchRequested() {
        return callback.onSearchRequested();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onSearchRequested(SearchEvent searchEvent) {
        return callback.onSearchRequested(searchEvent);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        return null;
    }


    @Override
    public void onActionModeStarted(ActionMode mode) {
        callback.onActionModeStarted(mode);
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        callback.onActionModeFinished(mode);
    }
}
