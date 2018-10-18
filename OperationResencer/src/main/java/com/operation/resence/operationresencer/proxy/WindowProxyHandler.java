package com.operation.resence.operationresencer.proxy;

import android.util.Log;

import com.operation.resence.operationresencer.utils.Constants;
import com.operation.resence.operationresencer.utils.HookHelper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;

public class WindowProxyHandler implements InvocationHandler {
    private Object object;

    public WindowProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.v("fragmenthook", "invoke " + method.getName());
        if(method.getName().contains("onWindowFocusChanged")){
            boolean hasfous = true;
            if(args.length > 0){
                hasfous = (Boolean)args[0];
            }
            Constants.focusWindowActivityName = Constants.currentActivity.getClass().getSimpleName();
            Constants.hasFocus = hasfous;
            HookHelper.hookWindowManagerGlobal();
//            callback.onWindowFocusChanged(hasfous);
//            boolean hasfous args[0]
        }
        return method.invoke(object, args);
    }

    /**
     * 调用Proxy.newProxyInstance即可生成一个代理对象
     *
     * @param object
     * @return
     */
    public static Object newProxyInstance(Object object) {
        // 传入被代理对象的classloader实现的接口, 还有DynamicProxyHandler的对象即可。
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new WindowProxyHandler(object));
    }
}