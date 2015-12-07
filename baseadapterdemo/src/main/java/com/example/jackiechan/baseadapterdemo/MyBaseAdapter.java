package com.example.jackiechan.baseadapterdemo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by jackiechan on 15/12/7.
 */
public abstract class MyBaseAdapter <T> extends BaseAdapter {
    private  List<T> list;//封装有数据的数据源
    private Context context;
    private int resId;//listview item 的资源 id

    public MyBaseAdapter(List<T> list, Context context, int resId) {
        this.list = list;
        this.context = context;
        this.resId = resId;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.getHolder(convertView, context, resId);
        //需要显示内容
        setData(holder,position);
        return holder.getmConvertView();
    }
    public abstract void setData(ViewHolder viewHolder,int position);
}
