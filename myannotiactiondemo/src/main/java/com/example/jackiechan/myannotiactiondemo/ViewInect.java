package com.example.jackiechan.myannotiactiondemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jackiechan on 15/12/7.
 * 用于替代 findviewbyid 的
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewInect {
    int value();//控件的 id
}
