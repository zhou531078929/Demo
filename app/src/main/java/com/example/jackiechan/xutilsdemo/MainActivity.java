package com.example.jackiechan.xutilsdemo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.xutils.ImageManager;
import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

public class MainActivity extends AppCompatActivity {

    private ImageView iv1,iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = ((ImageView) findViewById(R.id.iv1));
        iv2 = ((ImageView) findViewById(R.id.i2));
//        RequestParams requestParams = new RequestParams("http://www.baidu.com");
//        Callback.Cancelable cancelable = x.http().get(requestParams, new Callback.CommonCallback<String>() {//回调的泛型是返回的数据类型
//            @Override
//            public void onSuccess(String result) {//成功的时候的回调
//                LogUtil.e(result);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {//失败的回调
//                LogUtil.e(ex.getMessage() + "====onerror");
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {//取消后的回调
//                LogUtil.e(cex.getMessage() + "====onCancelled");
//            }
//
//            @Override
//            public void onFinished() {//完成的回调,这个方法总是会执行
//                LogUtil.e("====finish");
//            }
//        });
//        x.http().request(HttpMethod.POST, new RequestParams(), new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });//第一个参数是请求类型,第二个参数是请求的参数,第三个是回调
        // cancelable.cancel();//取消请求即可
//        RequestParams post = new RequestParams();
//        post.addBodyParameter("key","vlaue");//添加 post 请求的参数
//        post.addBodyParameter("file", new File("filepath"));//添加上传文件
//        x.http().post();//发送 post 请求

        ImageManager imageManager = x.image();//2.x 版本中的 bitmaputils,这个可以覆用的,只需要创建一个即可, http 的则需要每次都创建
        imageManager.bind(iv1,"http://imgs.xiuna.com/qianhou/2012-4-26/3/5.jpg");
        imageManager.bind(iv2, "http://imgs.xiuna.com/qianhou/2012-4-26/3/6.jpg", new Callback.CommonCallback<Drawable>() {
            @Override
            public void onSuccess(Drawable result) {//一般是用来额外操作返回图片的,比如写入到文件中
                LogUtil.e("onSuccess");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("onError");
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled");
            }

            @Override
            public void onFinished() {
                LogUtil.e("onFinished");
            }
        });
        ImageOptions.Builder builder = new ImageOptions.Builder();
        builder.setCrop(true);
        builder.setRadius(30);
        builder.setAnimation(AnimationUtils.loadAnimation(this, R.anim.a));//设置加载完成后的动画
        imageManager.bind(iv1,"http://imgs.xiuna.com/qianhou/2012-4-26/3/5.jpg",builder.build());
    }
}
