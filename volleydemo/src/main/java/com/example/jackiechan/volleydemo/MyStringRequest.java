package com.example.jackiechan.volleydemo;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by jackiechan on 15/12/7.
 */
public class MyStringRequest extends StringRequest {
    private Map<String,String> stringStringMap;
    public MyStringRequest(int method, String url,BaseJsonObject baseJsonObject,Map<String,String> stringStringMap) {
        super(method, url,  new MyStringRquestCallBck(baseJsonObject),new MyErrorListener());
        this.stringStringMap = stringStringMap;
        MyAppp.requestQueue.add(this);
    }

    public MyStringRequest(String url,BaseJsonObject baseJsonObject) {
//        super(url, new MyStringRquestCallBck(baseJsonObject),new MyErrorListener());
//        MyAppp.requestQueue.add(this);
        this(Method.GET, url, baseJsonObject,null);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return stringStringMap;
    }
}
