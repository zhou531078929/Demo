package com.example.meterialdesigndemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 扁平化 简介,水波纹反馈 比较良好的动画体验
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navilayout);
//        final TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textinout);
//        textInputLayout.setHint("请输入用户名");
//        EditText editText = textInputLayout.getEditText();
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() > 10) {
//                    textInputLayout.setError("用户名长度不能超过10");
//                    textInputLayout.setErrorEnabled(true);//启用错误提示
//
//                }else{
//                    textInputLayout.setErrorEnabled(false);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tab);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//        tabLayout.addTab(tabLayout.newTab().setText("tab1"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab2"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab3"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab4"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab5"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab1"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab2"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab3"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab4"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab5"));
        List<String> titiles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String titile = "Tab" + (i + 1);
            tabLayout.addTab(tabLayout.newTab().setText(titile));
            titiles.add(titile);
            MyFragment myFragment = new MyFragment("fragment" + (i + 1));
            fragments.add(myFragment);
        }
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments, titiles);
        viewPager.setAdapter(myFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(myFragmentAdapter);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawlayout);
        drawerLayout.closeDrawers();//关闭侧滑
    }

    public void onClikc(View view) {
        final Snackbar snackbar = Snackbar.make(view, "这是一个提示", Snackbar.LENGTH_LONG);
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
