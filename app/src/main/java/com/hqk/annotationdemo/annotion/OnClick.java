package com.hqk.annotationdemo.annotion;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
// 注解继承   能  1  不能
@EventBase(listenerSetter="setOnClickListener",listenerType = View.OnClickListener.class,callbackMethod ="onClick" )
public @interface OnClick{
    int[] value();

}
