package com.example.jackiechan.volleydemo;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by jackiechan on 15/12/7.
 */
public class MyErrorListener implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {
        //在此提示用户 或者发送一个专门用于封装错误的对象
    }
}
