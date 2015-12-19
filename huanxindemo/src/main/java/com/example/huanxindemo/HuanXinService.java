package com.example.huanxindemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.easemob.chat.EMChat;

public class HuanXinService extends Service {
    public HuanXinService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        HuanXinUtils.login(intent.getStringExtra("name"),intent.getStringExtra("pwd"));//登录
        HuanXinUtils.registConnectionListener(this);//注册状态链接的监听
        HuanXinUtils.registBroadReciver(this);//注册接受消息的广播
        EMChat.getInstance().setAppInited();//通知可以收消息了
        return super.onStartCommand(intent, flags, startId);
    }
}
