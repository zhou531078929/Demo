package com.example.jackiechan.myhttputils;

import java.util.Map;

/**
 * Created by jackiechan on 15/12/7.
 */
public class Utils {

    public static void get(Map map) {
        //反射获取到调用的类, 然后反射获取对应的数据类型的注解,获取到解析格式的对象包名,根据工厂模式 通过包名返回请求地址, 具体的参数是 map 传递过来的
        //在这里新建请求参数,将 map 中的参数放进去,地址放进去
    }
}
