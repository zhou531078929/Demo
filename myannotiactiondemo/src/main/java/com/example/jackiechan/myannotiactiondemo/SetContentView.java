package com.example.jackiechan.myannotiactiondemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jackiechan on 15/12/7.
 *
 * 这个方法用于给 activity 设置 contentview 布局的
 */
@Target(ElementType.TYPE)//注解的范围是类
@Retention(RetentionPolicy.RUNTIME)//声明周期保持到运行
public @interface SetContentView {
    int value();//这个是资源 id
}
