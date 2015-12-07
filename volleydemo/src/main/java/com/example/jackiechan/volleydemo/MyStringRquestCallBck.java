package com.example.jackiechan.volleydemo;

import com.android.volley.Response;
import com.google.gson.Gson;

import de.greenrobot.event.EventBus;

/**
 * Created by jackiechan on 15/12/7.
 */
public class MyStringRquestCallBck implements Response.Listener<String> {
    private BaseJsonObject object;

    public MyStringRquestCallBck(BaseJsonObject object) {
        this.object = object;
    }

    @Override
    public void onResponse(String response) {
        String tag = object.tag;//先获取身上的 tag
        object = new Gson().fromJson(response, object.getClass());// 解析用新的对象覆盖
        object.tag = tag;//重新设置 tag
        EventBus.getDefault().post(object);
    }
}
