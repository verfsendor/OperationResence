package com.operation.resence.operationresencer;
import android.app.Activity;
import android.app.Instrumentation;
import android.os.Bundle;
import android.view.Window;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * Created by xuzhendong on 2018/9/7.
 */

public  class InstrumentationProxy extends Instrumentation {

    private static final String TAG = "ActivityProxyInstrumentation";

    // ActivityThread中原始的对象, 保存起来
    Instrumentation mBase;

    public InstrumentationProxy(Instrumentation base) {
        mBase = base;
    }

    /**
     * Perform calling of an activity's {@link Activity#onCreate}
     * method.  The default implementation simply calls through to that method.
     *
     * @param activity The activity being created.
     * @param icicle   The previously frozen state (or null) to pass through to onCreate().
     */
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        try {
            HookHelper.HookFragment(activity.getFragmentManager());
            if(activity.getWindow() != null) {
                Window window = activity.getWindow();
                window.setCallback(new HookTouchEvent(window.getCallback()));
                Method callActivityOnCreate = Instrumentation.class.getDeclaredMethod("callActivityOnCreate", Activity.class, Bundle.class);
                callActivityOnCreate.setAccessible(true);
                callActivityOnCreate.invoke(mBase, activity, icicle);
            }
            if(activity.getWindow() != null && activity.getWindow().getDecorView() != null){
                ViewManager.getEvent(activity.getClass().getSimpleName(), activity.getWindow().getDecorView());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

