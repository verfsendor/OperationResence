package com.operation.resence.operationresencer;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import com.operation.resence.operationresencer.bean.KeyEventBean;
import com.operation.resence.operationresencer.utils.OperationResencer;
import java.util.List;

/**
 * Created by xuzhendong on 2018/9/7.
 */

public class HookTouchEvent implements Window.Callback {
    Window.Callback mCallback;
    public HookTouchEvent (Window.Callback callback){
        this.mCallback = callback;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(OperationResencer.test){
            KeyEventBean keyEventBean = new KeyEventBean();
            keyEventBean.setTime(System.currentTimeMillis());
            keyEventBean.setKeyCode(event.getKeyCode());
            keyEventBean.setPageName(mCallback.getClass().getSimpleName());
            OperationResencer.addEvent(keyEventBean);
        }
        mCallback.dispatchKeyEvent(event);
        return mCallback.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        mCallback.dispatchKeyShortcutEvent(event);
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(OperationResencer.test){
//            TouchEventBean eventBean = new TouchEventBean();
//            eventBean.setRawX(event.getRawX());
//            eventBean.setRawY(event.getRawY());
//            eventBean.setTime(System.currentTimeMillis());
//            eventBean.setAction(event.getAction());
//            eventBean.setPageName(mCallback.getClass().getSimpleName());
//            OperationResencer.addEvent(eventBean);
        }else {
//            ViewManager.setEventToView(Constants.nowActivityName.getWindow().getDecorView(),event,"-0-1-0-0-0-1","");
        }
        return mCallback.dispatchTouchEvent(event);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent event) {
        mCallback.dispatchTrackballEvent(event);
        return false;
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        mCallback.dispatchGenericMotionEvent(event);
        return false;
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        mCallback.dispatchPopulateAccessibilityEvent(event);
        return false;
    }

    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
        mCallback.onCreatePanelView(featureId);
        return null;
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        mCallback.onCreatePanelMenu(featureId, menu);
        return false;
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        mCallback.onPreparePanel(featureId,view,menu);
        return false;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        mCallback.onMenuOpened(featureId,menu);
        return false;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        mCallback.onMenuItemSelected(featureId, item);
        return false;
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams attrs) {
       mCallback.onWindowAttributesChanged(attrs);
    }

    @Override
    public void onContentChanged() {
      mCallback.onContentChanged();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
      mCallback.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onAttachedToWindow() {
      mCallback.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
     mCallback.onDetachedFromWindow();
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
     mCallback.onPanelClosed(featureId, menu);
    }

    @Override
    public boolean onSearchRequested() {
        mCallback.onSearchRequested();
        return false;
    }

    @Override
    public boolean onSearchRequested(SearchEvent searchEvent) {
        mCallback.onSearchRequested();
        return false;
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        mCallback.onWindowStartingActionMode(callback);
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        mCallback.onWindowStartingActionMode(callback,type);
        return null;
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
       mCallback.onActionModeStarted(mode);
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        mCallback.onActionModeFinished(mode);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {
        mCallback.onProvideKeyboardShortcuts(data,menu,deviceId);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
       mCallback.onPointerCaptureChanged(hasCapture);
    }
}
