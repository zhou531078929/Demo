package com.example.pulltorefreshdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener {
    private boolean isCanrefresh;//是否可以分页
    private boolean isLoading;//是否正在加载数据
    private PullToRefreshListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (PullToRefreshListView) findViewById(R.id.list_view);
        listview.setMode(PullToRefreshBase.Mode.BOTH);//加载的方式 单独上拉  下拉还是一起
        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //下拉
                Log.e("pull", "下拉刷新开始了");
                handler.sendEmptyMessageDelayed(1, 3000);//用于伪装网络请求延迟的

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                ///上拉
                Log.e("pull", "上拉加载更多新开始了");
                handler.sendEmptyMessageDelayed(1, 3000);//用于伪装网络请求延迟的
            }
        });
//        ListView listView = null;
//        listView.setOnScrollListener(this);
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           listview.onRefreshComplete();//通知刷新完毕,当网络请求返回结果的时候 结果可以是失败
        }
    };
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //停止的时候分页
        if (scrollState == SCROLL_STATE_IDLE && isCanrefresh) {
            isCanrefresh = false;
            isLoading = true;
            //加载数据, 数据加载完成或者是出错的时候 应该把 isloading置为false
        }
    }

    /**
     * @param view                     指的就是listview
     * @param firstVisibleItem         第一条可见的item的位置
     * @param visibleItemCount//可见的数量
     * @param totalItemCount//listview item的总数量
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {//代表最后一条进入屏幕
            //gettop  getbottom  getleft  getright
            //获取到最后一条item 然后判断它的getbottom是不是小于等于父控件的高度
            View childAt = view.getChildAt(visibleItemCount - 1);//这个方法获取的是屏幕内显示的控件,第一条是0 ,总共有visibleItemCount条,那最后一条就是visibleItemCount-1
            if (childAt != null && childAt.getBottom() <= view.getHeight() && !isLoading) {
                isCanrefresh = true;
            }
        }
    }
}
