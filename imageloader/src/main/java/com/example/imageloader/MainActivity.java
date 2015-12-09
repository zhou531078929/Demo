package com.example.imageloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        ImageLoader imageLoader = new ImageLoader(MyApp.requestQueue,MyApp.imageCache);

        //这个listener 无法解决listview中滑动的图片错误问题
//        ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(iv, R.mipmap.ic_launcher, R.mipmap.error);
        iv.setTag("http://c.hiphotos.baidu.com/image/pic/item/5bafa40f4bfbfbed91fbb0837ef0f736aec31faf.jpg");
        MyImageListenet imageListener = new MyImageListenet(iv);
        imageLoader.get("http://c.hiphotos.baidu.com/image/pic/item/5bafa40f4bfbfbed91fbb0837ef0f736aec31faf.jpg", imageListener);
    }
}
