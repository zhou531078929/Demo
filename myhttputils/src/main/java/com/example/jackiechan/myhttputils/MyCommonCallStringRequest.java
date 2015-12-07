package com.example.jackiechan.myhttputils;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import org.xutils.common.Callback;

/**
 * Created by jackiechan on 15/12/7.
 */
public class MyCommonCallStringRequest implements Callback.CommonCallback<String> {
    private Object object;//用于解析 json 数据的对象,需要什么就传递什么
    private Handler handler;//用于发送消息的 handler
    private int what;// 消息的 what

    public MyCommonCallStringRequest(Object object, Handler handler, int what) {
        this.object = object;
        this.handler = handler;
        this.what = what;
    }

    @Override
    public void onSuccess(String result) {
        object = new Gson().fromJson(result, object.getClass());//解析最终生成的对象类型 是由object.getClass() 来决定的,也就是说你传递进来了什么类型最终就解析成什么类型
        Message message = handler.obtainMessage();
        message.what = what;
        message.obj = object;
        handler.sendMessage(message);
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
