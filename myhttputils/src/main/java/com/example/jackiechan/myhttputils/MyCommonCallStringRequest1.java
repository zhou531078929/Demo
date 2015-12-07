package com.example.jackiechan.myhttputils;

import com.google.gson.Gson;

import org.xutils.common.Callback;

import de.greenrobot.event.EventBus;

/**
 * Created by jackiechan on 15/12/7.
 */
public class MyCommonCallStringRequest1 implements Callback.CommonCallback<String> {
    private Object object;//用于解析 json 数据的对象,需要什么就传递什么

    public MyCommonCallStringRequest1(Object object) {
        this.object = object;
    }
    @Override
    public void onSuccess(String result) {
        object = new Gson().fromJson(result, object.getClass());//解析最终生成的对象类型 是由object.getClass() 来决定的,也就是说你传递进来了什么类型最终就解析成什么类型
        EventBus.getDefault().post(object);

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
