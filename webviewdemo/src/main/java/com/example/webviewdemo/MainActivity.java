package com.example.webviewdemo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webview;

    @SuppressLint("JavascriptInterface")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView) findViewById(R.id.webview);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);//启用js支持
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置javascript可以弹出窗口
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载的时候调用
                //在这里可以弹出进度条
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {//页面加载完成的情况下调用
                //关闭进度条
                super.onPageFinished(view, url);
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {//当进度发生变化时,在这里可以判断页面是否完全加载出来
                super.onProgressChanged(view, newProgress);
            }

            //弹出一个对话框
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.e("js", "onJsAlert==url" + url + "===message===" + message + "=====result====" + result);
                //如果想要弹出自己的对话框,可以在这里写
                return super.onJsAlert(view, url, message, result);
            }

            //弹出一个带确定取消的对话框
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                Log.e("js", "onJsAlert==url" + url + "===message===" + message + "=====result====" + result);
                //可以自定义弹出对话框,返回值 取决于自定义对话框点击的是确定还是取消
                return super.onJsConfirm(view, url, message, result);
            }

            //弹出一个带默认输入的对话框
            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                Log.e("js", "onJsAlert==url" + url + "===message===" + message + "=====result====" + result+"====defaultValue==="+defaultValue);
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }
        });

        webview.addJavascriptInterface(new Object(){
            @android.webkit.JavascriptInterface
            public void callFromJs(String s){
                Log.e("js", "第87行" + s);
            }

        },"qianfeng");
        webview.loadUrl("file:///android_asset/webview/jstest.html");
    }

    public void onClick(View view) {
        webview.loadUrl("javascript:testAlert();");//加载js脚本
    }
}
