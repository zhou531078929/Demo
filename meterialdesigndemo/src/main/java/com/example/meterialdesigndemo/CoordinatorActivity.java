package com.example.meterialdesigndemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class CoordinatorActivity extends AppCompatActivity {
    private  Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coordinator_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coo);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.tollbarlayout);
        collapsingToolbarLayout.setTitle("这是标题文本");

    }


    public void onClikc(View view) {
        final Snackbar snackbar = Snackbar.make(coordinatorLayout, "这是一个提示", Snackbar.LENGTH_LONG);
        snackbar.setAction("我知道了", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }
}
