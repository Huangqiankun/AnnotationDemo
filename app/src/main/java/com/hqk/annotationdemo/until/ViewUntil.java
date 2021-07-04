package com.hqk.annotationdemo.until;

import android.view.View;

import com.hqk.annotationdemo.annotion.ContentView;
import com.hqk.annotationdemo.annotion.ViewInject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ViewUntil {

    public static void inject(Object context) {
        injectLayout(context);
        injectView(context);
    }


    private static void injectView(Object context) {
        Class<?> aClass = context.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if (viewInject == null) {
                continue;
            }
            int valueId = viewInject.value();
            try {
                Method method = aClass.getMethod("findViewById", int.class);
                View view = (View) method.invoke(context, valueId);
//                view   btn  产生联系
                field.setAccessible(true);
                field.set(context, view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void injectLayout(Object context) {
        int layouyId = 0;
        Class<?> clazz = context.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView == null) {
            return;
        }
        //拿到注解里面的设置的布局id
        layouyId = contentView.value();

        try {
            //        通过反射拿到 setContentView 方法，然后调用该方法时，将注解得到的布局id，传入进去，从而实现 加载布局的功能
            Method contentMethod = context.getClass().getMethod("setContentView", int.class);
            contentMethod.invoke(context, layouyId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
