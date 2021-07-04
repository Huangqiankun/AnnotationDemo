package com.hqk.annotationdemo.until;

import android.view.View;

import com.hqk.annotationdemo.annotion.ContentView;
import com.hqk.annotationdemo.annotion.EventBase;
import com.hqk.annotationdemo.annotion.ViewInject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ViewUntil {

    public static void inject(Object context) {
        injectLayout(context);
        injectView(context);
        injectClick(context);
    }

    private static void injectClick(Object context) {
        //拿到绑定的对象
        Class<?> clazz = context.getClass();
        //获取绑定对象所有的方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<?> annotionType = annotation.annotationType();
                EventBase eventBase = annotionType.getAnnotation(EventBase.class);
                if (eventBase == null) {
                    continue;
                }
//                拿到事件三要素
                /**
                 * setOnLongClickListener
                 */
                String listenerSetter = eventBase.listenerSetter();
                /**
                 * OnLongClickListener.class
                 */
                Class<?> listenerType = eventBase.listenerType();
                /**
                 * 事件被触发之后，执行的回调方法的名称
                 * @return
                 *
                 * onLongClick
                 */
                String callBackMethod = eventBase.callbackMethod();
                try {
                    Method valueMethod = annotionType.getDeclaredMethod("value");
                    int[] viewId = (int[]) valueMethod.invoke(annotation);
                    for (int id : viewId) {
//                        有多少个按钮 id ---》findviewById   View.setOnClickerListener
                        Method findViewById = clazz.getMethod("findViewById", int.class);
                        View view = (View) findViewById.invoke(context, id);
                        ListenerInvocationHandler listenerInvocationHandler = new ListenerInvocationHandler(context, method);
                        Method setListener = view.getClass().getMethod(listenerSetter, listenerType);
                        Object proxy = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                new Class[]{listenerType}, listenerInvocationHandler);
                        setListener.invoke(view, proxy);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * btn  button    ====> value  R.id.btn  ===>findviewbyid
             *      btn.setOnClickListener(new View.OnClickListener() {
             *             @Override
             *             public void onClick(View v) {
             *
             *             }
             *         });
             */
//            setOnClickListener   Method  设置
//

        }


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
