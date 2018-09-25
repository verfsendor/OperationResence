package com.operation.resence.operationresencer.proxy;
import android.util.Log;
import android.view.View;

import com.operation.resence.operationresencer.ViewManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xuzhendong on 2018/9/19.
 */

public class DynamicProxy implements InvocationHandler
{
    //　这个就是我们要代理的真实对象
    private Object subject;

    //    构造方法，给我们要代理的真实对象赋初值
    public DynamicProxy(Object subject)
    {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] args)
            throws Throwable {
        Log.v("verf","mothod name " + method.getName());
        if(method.getName().contains("addView")){
            Log.v("zzzz","addView");
            View view = (View)args[0];
            ViewManager.travelView("",view);
            Log.v("zzzz", " " + view.getClass().getSimpleName());
            for(int i = 0; i < args.length; i ++){
                Log.v("zzzz"," " + i + " " + args[i]);
            }
        }
        Log.v("zzzz","before rent house");
        Log.v("zzzz","Method:" + method);
        //    当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(subject, args);
        //　　在代理真实对象后我们也可以添加一些自己的操作
        Log.v("zzzz","after rent house");
        Log.v("zzzz","Method:" + method);
        return null;
    }

}
