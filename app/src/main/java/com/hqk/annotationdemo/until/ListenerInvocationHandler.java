package com.hqk.annotationdemo.until;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//处理器
public class ListenerInvocationHandler implements InvocationHandler {
    private Object context;
    private Method activityMethod;

    private  final String TAG = "Handler";

    public ListenerInvocationHandler(Object context, Method activityMethod) {
        this.context = context;
        this.activityMethod = activityMethod;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.i(TAG, "invoke: ----调用前");
        return activityMethod.invoke(context, args);
    }
}
