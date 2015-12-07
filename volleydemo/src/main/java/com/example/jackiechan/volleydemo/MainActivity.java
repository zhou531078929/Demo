package com.example.jackiechan.volleydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

//        StringRequest stringRequest = new StringRequest("http://appapi.yaochufa.com/v2/ProductPackage/GetCommonPackagesByLinkIdV424?linkId=115829&machineCode=ffffffff-a50f-4cc6-ffff-ffffbd649aff&version=4.4.0&checkOutDate=2015-12-22&system=android&checkInDate=2015-11-17&channel=360mobile&isSelected=false", new MyStringRquestCallBck(new Data1(MainActivity.class.getName())), new MyErrorListener());
//        StringRequest stringRequest=new StringRequest("http://www.baidu.com", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.e("volley", response + "");
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("volley", ""+error.getMessage());
//            }
//        });

//        StringRequest stringRequest1=new StringRequest(StringRequest.Method.POST, "url", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {//post 请求用于获取参数数据的
//                Map map = new HashMap();
//                map.put("aa", "Fsdf");
//                return map;
//            }
//        };
        startActivity(new Intent(this, Main2Activity.class));
//        MyAppp.requestQueue.add(stringRequest);//一定要加到队列中,否则请求不执行
        MyStringRequest myStringRequest = new MyStringRequest("http://appapi.yaochufa.com/v2/ProductPackage/GetCommonPackagesByLinkIdV424?linkId=115829&machineCode=ffffffff-a50f-4cc6-ffff-ffffbd649aff&version=4.4.0&checkOutDate=2015-12-22&system=android&checkInDate=2015-11-17&channel=360mobile&isSelected=false", new Data1(MainActivity.class.getName()));
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "", new JSONObject(), new MyStringRquestCallBck(new Data1()),new MyErrorListener());//第三个参数, jsonobject 这个是请求的参数,有些网络请求要求传一个 json 字符串作为参数,而且这个 json 没有 key, 将 json 字符串封装成 jsonobject, 通过这个请求传递出去就可以
    }


    //    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//        }
//    };
    @Subscribe
    public void getData(Data1 data1) {
        if (this.getClass().getName().equals(data1.tag)) {
            Log.e("volley", "main======" + data1.getStatusCode());
        } else {
            Log.e("volley", "main======标记不一致不予处理");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
