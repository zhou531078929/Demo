package com.example.jackiechan.myannotiactiondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

@SetContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInect(R.id.tv)
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ViewInjectUtils.inject(this);
        tv.setText("这是自定义注解设置的内容");
    }
}
