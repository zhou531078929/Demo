package com.example.slidemenusdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {
    SlidingMenu menu;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT_RIGHT);//设置侧滑的方向,LEFT指的是侧滑在左侧
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//设置策划的触摸范围.full指的是全屏任何位置都可以滑出策划,MARGIN指的是靠近滑出范围的区域,NONE指的是不滑
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setBehindWidth(300);//设置侧滑栏的宽度
        menu.setMenu(R.layout.sliding);//设置菜单显示的内容
        menu.setSecondaryMenu(R.layout.sliding);//如果指定了左右均有侧滑,需要设置第二菜单
//        handler.sendEmptyMessageDelayed(1, 1000);
        tv1 = (TextView) findViewById(R.id.bt1);//只要把策划添加到activity中,它的布局就属于activity的一部分,就可以通过activityd额findviewbyid找到
        ((TextView) menu.findViewById(R.id.bt2)).setText("bbbfsdfdsfsafsad");
        tv1.setText("asdasdasdsadadad");
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            menu.toggle();//开关,当前状态取反显示
            sendEmptyMessageDelayed(1, 3000);
        }
    };
}
