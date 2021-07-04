package com.hqk.annotationdemo.annotion;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
// 注解继承   能  1  不能
@EventBase(listenerSetter="setOnLongClickListener",listenerType =
        View.OnLongClickListener.class,callbackMethod ="onLongClick" )
public @interface OnLongClick {
    int[] value();

}
