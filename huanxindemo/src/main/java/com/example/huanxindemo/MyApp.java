package com.example.huanxindemo;

import android.app.Application;

import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;

/**
 * Created by jackiechan on 15/12/19.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EMChat.getInstance().init(this);//初始化环信的sdk
        /**
         * debugMode == true 时为打开，sdk 会在log里输入调试信息
         * @param debugMode
         * 在做代码混淆的时候需要设置成false
         */
        EMChat.getInstance().setDebugMode(true);//在做打包混淆时，要关闭debug模式，避免消耗不必要的资源
        EMChatManager.getInstance().getChatOptions().setShowNotificationInBackgroud(false);//取消程序在后台的时候通过通知来提示,全部都走广播接收消息,如果你用的是环信的数据库,则可以不调用这个,如果是自己的 需要调用, 然后再收到消息的广播里面通过判断程序是否在后台 来决定自己发通知
    }
}
