package com.example.myslidingmenudemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.myslidingmenudemo.view.MySlidingMenu;

public class MainActivity extends AppCompatActivity {

    private MySlidingMenu mySildMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySildMenu = (MySlidingMenu) findViewById(R.id.slid);
    }

    public void onClick(View view) {
        mySildMenu.toggle();
    }
}
