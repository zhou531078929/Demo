package com.example.jackiechan.xutilsdemo;

import android.util.Log;

/**
 * Created by jackiechan on 15/12/5.
 */
public class MyLogUtils {
    private static  final  boolean ISDEBUG=true;//是否处于开发状态

    public  static  void e(String messeng) {
        if (ISDEBUG) {

            Log.e("com.example.jackiechan.xutilsdemo", ""+messeng);//如果打印 null 会出空指针异常
        }
    }
    public  static  void d(String messeng) {
        if (ISDEBUG) {

            Log.d("com.example.jackiechan.xutilsdemo", ""+messeng);//如果打印 null 会出空指针异常
        }
    }
    public  static  void i (String messeng) {
        if (ISDEBUG) {

            Log.i("com.example.jackiechan.xutilsdemo", ""+messeng);//如果打印 null 会出空指针异常
        }
    }
    public  static  void w(String messeng) {
        if (ISDEBUG) {

            Log.w("com.example.jackiechan.xutilsdemo", ""+messeng);//如果打印 null 会出空指针异常
        }
    }
    public  static  void v(String messeng) {
        if (ISDEBUG) {

            Log.v("com.example.jackiechan.xutilsdemo", ""+messeng);//如果打印 null 会出空指针异常
        }
    }
}
