package com.example.imageloader;

import android.app.Application;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by jackiechan on 15/12/9.
 */
public class MyApp extends Application {
    public static RequestQueue requestQueue;
    private static LruCache<String,Bitmap> bitmapLruCache;
    public  static ImageLoader.ImageCache imageCache;
    @Override
    public void onCreate() {
        super.onCreate();
        bitmapLruCache=new LruCache<String,Bitmap>((int) (Runtime.getRuntime().maxMemory()/8/1024)){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
      imageCache=new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return bitmapLruCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                bitmapLruCache.put(url, bitmap);
            }
        };
        requestQueue = Volley.newRequestQueue(this);
    }
}
