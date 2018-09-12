package com.operation.resence.operationresencer;
import android.app.FragmentManager;
import android.app.Instrumentation;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.operation.resence.operationresencer.proxy.InstrumentationProxy;
import com.operation.resence.operationresencer.proxy.OnKeyListenerProxy;
import com.operation.resence.operationresencer.proxy.OnTouchListenerProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by xuzhendong on 2018/9/7. InputMethodManager
 */

public class HookHelper {


    /**
     * hook 替换系统中的ActivityThread类中的Instrumentation对象
     * @throws Exception
     */
    public static void replaceInstrumentation() throws Exception {
        // 先获取到当前的ActivityThread对象
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
        currentActivityThreadMethod.setAccessible(true);
        //currentActivityThread是一个static函数所以可以直接invoke，不需要带实例参数
        Object currentActivityThread = currentActivityThreadMethod.invoke(null);

        // 拿到原始的 mInstrumentation字段
        Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
        mInstrumentationField.setAccessible(true);
        Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);
//         mInstrumentation.send
        // 创建代理对象
        Instrumentation evilInstrumentation = new InstrumentationProxy(mInstrumentation);
        // 偷梁换柱
        mInstrumentationField.set(currentActivityThread, evilInstrumentation);
    }

    public static void hookOnTouchEventListener(View view){
            try {
                Class viewClazz = Class.forName("android.view.View");
                //事件监听器都是这个实例保存的
                Method listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");
                if (!listenerInfoMethod.isAccessible()) {
                    listenerInfoMethod.setAccessible(true);
                }
                Object listenerInfoObj = listenerInfoMethod.invoke(view);

                Class listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");

                Field onTouchListenerField = listenerInfoClazz.getDeclaredField("mOnTouchListener");

                if (!onTouchListenerField.isAccessible()) {
                    onTouchListenerField.setAccessible(true);
                }
                View.OnTouchListener mOnTouchListener = (View.OnTouchListener) onTouchListenerField.get(listenerInfoObj);
                //自定义代理事件监听器
                Log.v("verf","montouch == null " +  (mOnTouchListener == null ? true : false));
                View.OnTouchListener onTouchListenerProxy = new OnTouchListenerProxy(mOnTouchListener);
                //更换
                onTouchListenerField.set(listenerInfoObj, onTouchListenerProxy);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void hookOnKeyEventListener(View view){
        try {
            Class viewClazz = Class.forName("android.view.View");
            //事件监听器都是这个实例保存的
            Method listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");
            if (!listenerInfoMethod.isAccessible()) {
                listenerInfoMethod.setAccessible(true);
            }
            Object listenerInfoObj = listenerInfoMethod.invoke(view);

            Class listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");

            Field onKeyListenerField = listenerInfoClazz.getDeclaredField("mOnKeyListener");

            if (!onKeyListenerField.isAccessible()) {
                onKeyListenerField.setAccessible(true);
            }
            View.OnKeyListener mOnKeyListener = (View.OnKeyListener) onKeyListenerField.get(listenerInfoObj);
            //自定义代理事件监听器
            View.OnKeyListener mOnKeyListenerProxy = new OnKeyListenerProxy(mOnKeyListener);
            //更换
            onKeyListenerField.set(listenerInfoObj, mOnKeyListenerProxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void hookFragment(FragmentManager manager){
        ViewGroup viewGroup = null ;

        try {
            Class<?> activityThreadClass = Class.forName("android.support.v4.app.BackStackRecord");
            Method replace = activityThreadClass.getDeclaredMethod("replace",Integer.class, Fragment.class, String.class);
            android.app.FragmentTransaction fragmentTransaction= manager.beginTransaction();
        }catch (Exception e){

        }
        // 先获取到当前的ActivityThread对象
    }

}
