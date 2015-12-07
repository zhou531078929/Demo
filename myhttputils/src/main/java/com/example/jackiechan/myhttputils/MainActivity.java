package com.example.jackiechan.myhttputils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.xutils.http.RequestParams;
import org.xutils.x;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class MainActivity extends AppCompatActivity {
    @DataInject("com.example.jackiechan.myhttputils.Data1")
    RequestParams requestParams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        RequestParams requestParams = new RequestParams("http://appapi.yaochufa.com/v2/ProductPackage/GetCommonPackagesByLinkIdV424?linkId=115829&machineCode=ffffffff-a50f-4cc6-ffff-ffffbd649aff&version=4.4.0&checkOutDate=2015-12-22&system=android&checkInDate=2015-11-17&channel=360mobile&isSelected=false");
        x.http().get(requestParams, new MyCommonCallStringRequest1(new Data1()));

//        x.http().get(requestParams, new MyCommonCallStringRequest(new Data1(), handler, 1));
//        RequestParams requestParams = new RequestParams("http://appapi.yaochufa.com/v2/ProductPackage/GetCommonPackagesByLinkIdV424?linkId=115829&machineCode=ffffffff-a50f-4cc6-ffff-ffffbd649aff&version=4.4.0&checkOutDate=2015-12-22&system=android&checkInDate=2015-11-17&channel=360mobile&isSelected=false");
//        x.http().get(requestParams, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//        RequestParams requestParams1 = new RequestParams("http://apiphp.yaochufa.com/portal/dest/scenicCity?version=4.4.0&imei=ffffffff-a50f-4cc6-ffff-ffffbd649aff&system=android&deviceToken=ffffffff-a50f-4cc6-ffff-ffffbd649aff&regId=0501808ed29&channel=yingyongbao&areaCode=110100&listType=around");
//        x.http().get(requestParams1, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
    }

    @Subscribe
    public void getData(Data1 dataClass) {
        Log.e("eventbus",""+ dataClass.getStatusCode());
    }

//    private Handler handler =new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    Data1 data1= (Data1) msg.obj;
//                    Log.e("myhttp", ""+data1.getStatusCode());
//                    break;
//            }
//        }
//    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
