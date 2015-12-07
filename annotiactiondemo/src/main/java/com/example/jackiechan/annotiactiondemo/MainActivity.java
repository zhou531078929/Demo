package com.example.jackiechan.annotiactiondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)//给 activity 注解设置 setcontentview 的
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.tv)
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        x.view().inject(this);//给这个 activity 增加注解的初始化
        tv.setText("aaaaaaaaa");
    }

    @Event(value = R.id.tv)
    private void onclick(View view) {//方法必须声明为私有的方法
        switch (view.getId()) {
            case R.id.tv:
                Log.e("onclick", "点击事件触发了");
                break;
        }
    }
}
