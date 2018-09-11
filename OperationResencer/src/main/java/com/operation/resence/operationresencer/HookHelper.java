package com.operation.resence.operationresencer;
import android.app.FragmentManager;
import android.app.Instrumentation;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * Created by xuzhendong on 2018/9/7. InputMethodManager
 */

public class HookHelper {
//    public static void replaceInstrumentation(Activity activity) throws Exception {
//        Class<?> k = Activity.class;
//        //通过Activity.class 拿到 mInstrumentation字段
//        Field field = k.getDeclaredField("mInstrumentation");
//        field.setAccessible(true);
//        //根据activity内mInstrumentation字段 获取Instrumentation对象
//        Instrumentation instrumentation = (Instrumentation) field.get(activity);
//        //创建代理对象
//        Instrumentation instrumentationProxy = new ActivityProxyInstrumentation(instrumentation);
//        //进行替换
//        field.set(activity, instrumentationProxy);
//
//    }

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

    public static void replaceWindowManagerGlobal() throws Exception {
        // 先获取到当前的ActivityThread对象
        Class<?> activityThreadClass = Class.forName("android.app.WindowManagerGlobal");
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


    public static void HookFragment(FragmentManager manager){
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
