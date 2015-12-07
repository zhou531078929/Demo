package com.example.jackiechan.myhttputils;

import android.app.Application;

import org.xutils.x;

/**
 * Created by jackiechan on 15/12/7.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
