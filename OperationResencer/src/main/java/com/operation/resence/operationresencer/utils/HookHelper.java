package com.operation.resence.operationresencer.utils;
import android.app.Instrumentation;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.operation.resence.operationresencer.proxy.InstrumentationProxy;
import com.operation.resence.operationresencer.proxy.OnClickListenerProxy;
import com.operation.resence.operationresencer.proxy.OnHierarchyChangeListenerProxy;
import com.operation.resence.operationresencer.proxy.OnKeyListenerProxy;
import com.operation.resence.operationresencer.proxy.OnTouchListenerProxy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by xuzhendong on 2018/9/7. InputMethodManager
 */

public class HookHelper {
    /**
     * 为了节省性能，将频繁反射时用到的部分Class和 Method,Field对象放在内存中保存
     */
    private static Class viewClazz;
    private static Class listenerInfoClazz;
    private static Method listenerInfoMethod;
    private static Field onKeyListenerField;
    private static Field onClickListenerField;
    private static Field onTouchListenerField;
    private static Class globalClass;
    private static Method getInstanceMethod;
    private static Field mViewsField;

    /**
     * hook 替换系统中的ActivityThread类中的Instrumentation对象
     * @throws Exception
     */
    public static void replaceInstrumentation() {
        try {
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void hookOnKeyEventListener(View view){
        try {
            if(viewClazz == null)
                viewClazz = Class.forName("android.view.View");
            //事件监听器都是这个实例保存的
            if(listenerInfoMethod == null)
                listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");

            if (!listenerInfoMethod.isAccessible()) {
                listenerInfoMethod.setAccessible(true);
            }
            Object listenerInfoObj = listenerInfoMethod.invoke(view);
            if(listenerInfoClazz == null)
                listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");
            if(onKeyListenerField == null)
                onKeyListenerField = listenerInfoClazz.getDeclaredField("mOnKeyListener");

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

    public static void hookViewClickListener(View view) {
        try {
            if(viewClazz == null)
                viewClazz = Class.forName("android.view.View");
            //事件监听器都是这个实例保存的
            if(listenerInfoMethod == null)
                listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");
            if (!listenerInfoMethod.isAccessible()) {
                listenerInfoMethod.setAccessible(true);
            }
            Object listenerInfoObj = listenerInfoMethod.invoke(view);
            if(listenerInfoClazz == null)
                listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");
            if(onClickListenerField == null)
                onClickListenerField = listenerInfoClazz.getDeclaredField("mOnClickListener");

            if (!onClickListenerField.isAccessible()) {
                onClickListenerField.setAccessible(true);
            }
            View.OnClickListener mOnClickListener = (View.OnClickListener) onClickListenerField.get(listenerInfoObj);
            //自定义代理事件监听器
            View.OnClickListener onClickListenerProxy = new OnClickListenerProxy(mOnClickListener);
            //更换
            onClickListenerField.set(listenerInfoObj, onClickListenerProxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hookOnHierarchyChangeListener(ViewGroup viewGroup) {
        try {
            Class viewGropClazz = Class.forName("android.view.ViewGroup");
            //事件监听器都是这个实例保存的
            Field mHierarchyFied = viewGropClazz.getDeclaredField("mOnHierarchyChangeListener");

            if (!mHierarchyFied.isAccessible()) {
                mHierarchyFied.setAccessible(true);
            }
            ViewGroup.OnHierarchyChangeListener listener = (ViewGroup.OnHierarchyChangeListener) mHierarchyFied.get(viewGroup);
            //自定义代理事件监听器
            OnHierarchyChangeListenerProxy proxy = new OnHierarchyChangeListenerProxy(listener);
            //更换
            Log.v("fragmenthook","hookOnHierarchyChangeListener setfrist " + viewGroup.getClass().getSimpleName());
            viewGroup.setOnHierarchyChangeListener(proxy);
            Log.v("fragmenthook","hookOnHierarchyChangeListenerset " + HookHelper.getOnHierarchyChangeListener(viewGroup));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ViewGroup.OnHierarchyChangeListener getOnHierarchyChangeListener(ViewGroup viewGroup) {
        Log.v("fragmenthook","hookOnHierarchyChangeListener 1");
        try {
            Class viewGropClazz = Class.forName("android.view.ViewGroup");
            //事件监听器都是这个实例保存的
            Field mHierarchyFied = viewGropClazz.getDeclaredField("mOnHierarchyChangeListener");

            if (!mHierarchyFied.isAccessible()) {
                mHierarchyFied.setAccessible(true);
            }
            ViewGroup.OnHierarchyChangeListener listener = (ViewGroup.OnHierarchyChangeListener) mHierarchyFied.get(viewGroup);
            return listener;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void hookOnTouchEventListener(View view){
        try {
            if(viewClazz == null)
                viewClazz = Class.forName("android.view.View");
            //事件监听器都是这个实例保存的
            if(listenerInfoMethod == null)
                listenerInfoMethod = viewClazz.getDeclaredMethod("getListenerInfo");
            if (!listenerInfoMethod.isAccessible()) {
                listenerInfoMethod.setAccessible(true);
            }
            Object listenerInfoObj = listenerInfoMethod.invoke(view);
            if(listenerInfoClazz == null)
                listenerInfoClazz = Class.forName("android.view.View$ListenerInfo");
            if(onTouchListenerField == null)
                onTouchListenerField = listenerInfoClazz.getDeclaredField("mOnTouchListener");

            if (!onTouchListenerField.isAccessible()) {
                onTouchListenerField.setAccessible(true);
            }
            View.OnTouchListener mOnTouchListener = (View.OnTouchListener) onTouchListenerField.get(listenerInfoObj);
            //自定义代理事件监听器
            View.OnTouchListener onTouchListenerProxy = new OnTouchListenerProxy(mOnTouchListener);
            //更换
            onTouchListenerField.set(listenerInfoObj, onTouchListenerProxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 反射获取WindowManagerGlobal中的view的list列表实例，顶层view即为当前获取焦点的view
     */
    public static void hookWindowManagerGlobal(){
        try {
            if(globalClass == null)
                globalClass = Class.forName("android.view.WindowManagerGlobal");
            if(getInstanceMethod == null)
                getInstanceMethod = globalClass.getDeclaredMethod("getInstance");
            if (!getInstanceMethod.isAccessible()) {
                getInstanceMethod.setAccessible(true);
            }
            Object globalObject = getInstanceMethod.invoke(null);
            if(mViewsField == null)
             mViewsField = globalClass.getDeclaredField("mViews");
            if (!mViewsField.isAccessible()) {
                mViewsField.setAccessible(true);
            }
            ArrayList<View> views = (ArrayList<View>) mViewsField.get(globalObject) ;
            /**
             * 遍历列表中的最新一个view，即为当前界面显示在最上方的view，判断tag非空以避免重复遍历，
             */
            if(views != null && views.size() > 0 && views.get(views.size() - 1).getTag() == null) {
                View view = views.get(views.size() - 1);
                Log.v("fragmenthook","遍历到一个弹窗 " + view.getTag() + " " + view);
                ViewHelper.travelView("popwindow&" + Constants.currentActivity.getClass().getSimpleName(), view);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射获取WindowManagerGlobal中的view的list列表实例，顶层view即为当前获取焦点的view
     */
    public static void hookFragmentManager(FragmentActivity activity){
        try {
            Class fragmentActivityClass = Class.forName("android.support.v4.app.FragmentActivity");

            Method getFragmentManagerMethod = fragmentActivityClass.getDeclaredMethod("getSupportFragmentManager");
            if (!getFragmentManagerMethod.isAccessible()) {
                getFragmentManagerMethod.setAccessible(true);
            }
            Object fragmentManagerImplObject = getFragmentManagerMethod.invoke(activity);
            Class fragmentManagerImplClass = Class.forName("android.support.v4.app.FragmentManagerImpl");
            Field mFragmentsField = fragmentManagerImplClass.getDeclaredField("mAdded");
            if (!mFragmentsField.isAccessible()) {
                mFragmentsField.setAccessible(true);
            }
            ArrayList<Fragment> fragments = (ArrayList<Fragment>) mFragmentsField.get(fragmentManagerImplObject) ;
            if(fragments != null){
                ViewHelper.travelView(fragments.get(fragments.size() - 1).getClass().getSimpleName(), fragments.get(fragments.size() - 1).getView());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
