package com.example.jackiechan.baseadapterdemo;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jackiechan on 15/12/7.
 */
public class MyAdaapter extends MyBaseAdapter<String> {
    private List<String> strings;
    public MyAdaapter(List<String> list, Context context, int resId) {
        super(list, context, resId);
        this.strings = list;//数据源赋值,一定要接收数据 否则下面调用的时候就崩了
    }

    @Override
    public void setData(ViewHolder viewHolder, int position) {
        ((TextView) viewHolder.findViewById(R.id.tv)).setText("这是第"+position);
    }
}
