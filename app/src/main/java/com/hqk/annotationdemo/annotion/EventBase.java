package com.hqk.annotationdemo.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface EventBase {
//setOnLongClickListener
    String listenerSetter();
    /**
     * 事件监听的类型
     * @return
     * OnLongClickListener.class
     */
    Class<?> listenerType();

    /**
     * 事件被触发之后，执行的回调方法的名称
     * @return
     *
     * onLongClick
     */
    String callbackMethod();

}
