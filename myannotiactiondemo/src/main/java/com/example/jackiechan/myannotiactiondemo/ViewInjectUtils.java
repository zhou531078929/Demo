package com.example.jackiechan.myannotiactiondemo;

import android.app.Activity;

/**
 * Created by jackiechan on 15/12/7.
 */
public class ViewInjectUtils {
    private static ViewInjectIml viewInjectIml = ViewInjectIml.getinstance();

    /**
     * 初始化注解
     * @param activity
     */
    public static  void inject(Activity activity) {
        viewInjectIml.inject(activity);
    }
}
