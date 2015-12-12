package com.example.satellitemenudemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ext.SatelliteMenu;
import android.view.ext.SatelliteMenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SatelliteMenu menu = (SatelliteMenu) findViewById(R.id.menu);

//		  Set from XML, possible to programmatically set
//        float distance = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170, getResources().getDisplayMetrics());
//        menu.setSatelliteDistance((int) distance);
//        menu.setExpandDuration(500);
//        menu.setCloseItemsOnClick(false);
//        menu.setTotalSpacingDegree(60);

        List<SatelliteMenuItem> items = new ArrayList<>();
        items.add(new SatelliteMenuItem(6, R.mipmap.ic_1));
        items.add(new SatelliteMenuItem(5, R.mipmap.ic_3));
        items.add(new SatelliteMenuItem(4, R.mipmap.ic_4));
        items.add(new SatelliteMenuItem(3, R.mipmap.ic_5));
        items.add(new SatelliteMenuItem(2, R.mipmap.ic_6));
        items.add(new SatelliteMenuItem(1, R.mipmap.ic_2));
//        items.add(new SatelliteMenuItem(5, R.drawable.sat_item));
        menu.addItems(items);

        menu.setOnItemClickedListener(new SatelliteMenu.SateliteClickedListener() {

            public void eventOccured(int id) {
                Log.e("sat", "Clicked on " + id);
            }
        });
    }
}
