package com.example.jackiechan.dbutils;

import android.app.Application;

import org.xutils.x;

/**
 * Created by jackiechan on 15/12/6.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
