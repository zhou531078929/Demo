package com.example.meterialdesigndemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by jackiechan on 15/12/20.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> strings;
    public MyFragmentAdapter(FragmentManager fm,List<Fragment> fragments,List<String> strings) {
        super(fm);
        this.fragments = fragments;
        this.strings = strings;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
