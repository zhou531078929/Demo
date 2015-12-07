package com.example.jackiechan.xutilsdemo;

import android.app.Application;

import org.xutils.x;

/**
 * Created by jackiechan on 15/12/5.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);//初始化Xutils
        x.Ext.setDebug(true);//如果用 logutils 的话需要设置这个 否则不打印日志
    }
}
