package com.example.jackiechan.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import de.greenrobot.event.EventBus;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    public void onClick(View view) {
        DataClass dataClass = new DataClass("你猜我给你了什么");
        EventBus.getDefault().post(dataClass);//发送数据出去
    }
}
