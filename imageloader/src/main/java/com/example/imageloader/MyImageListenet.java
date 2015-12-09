package com.example.imageloader;

import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by jackiechan on 15/12/9.
 */
public class MyImageListenet implements ImageLoader.ImageListener {
    private ImageView imageView;

    public MyImageListenet(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
        try {
            //要判断错位,需要知道请求的图片地址和返回的是否一致,不一致说明是复用前的返回,一致则代表是当前的请求
            String requestUrl = response.getRequestUrl();//是当前图片的请求地址
            String url = (String) imageView.getTag();//通过获取imageview的tag来拿到它想要的地址
            if (url != null && url.equals(requestUrl)) {//如果有tag并且和图片请求的地址一致,则设置图片
                imageView.setImageBitmap(response.getBitmap());
            }
        }catch (Exception e){
            imageView.setImageBitmap(response.getBitmap());
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
