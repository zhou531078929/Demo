package com.example.jackiechan.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EventBus.getDefault().register(this);

    }
    @Subscribe
    public void getData(Data1 data1) {
        if (this.getClass().getName().equals(data1.tag)) {
            Log.e("volley", "main2======" + data1.getStatusCode());
        }else{
            Log.e("volley", "main2======标记不一致不予处理" );
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
