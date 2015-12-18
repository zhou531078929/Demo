package com.example.myslidingmenudemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.myslidingmenudemo.R;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jackiechan on 15/12/18.
 */
public class MySlidingMenu extends HorizontalScrollView {
    private LinearLayout mLinearLayout;
    private ViewGroup mMenu, mContent;//菜单,内容区域
    private int mScreenwidth, mPaddingRight, mMenuWidth;//屏幕的宽度, 菜单距离屏幕右侧的距离, 菜单的宽度
    private boolean once, isopen;//是否第一次加载,是否是打开状态

    public MySlidingMenu(Context context) {
        this(context, null);
    }

    public MySlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    /**
     * 当设置使用了自定义属性的时候调用
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public MySlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MySlidingMenu, defStyleAttr, 0);
        int indexCount = a.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = a.getIndex(i);
            switch (index) {
                case R.styleable.MySlidingMenu_paddingRight:
                    mPaddingRight = a.getDimensionPixelSize(index, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
                    break;
            }
        }

        a.recycle();//用完一定要释放
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenwidth = displayMetrics.widthPixels;//获取屏幕的宽度
//        mPaddingRight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());
    }

    /**
     * 测量款高度,可能会多次调用
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mLinearLayout = (LinearLayout) getChildAt(0);
        if (!once) {
            mMenu = (ViewGroup) mLinearLayout.getChildAt(0);
            mContent = (ViewGroup) mLinearLayout.getChildAt(1);
            mMenuWidth = mMenu.getLayoutParams().width = mScreenwidth - mPaddingRight;//将屏幕的宽度减去距离右侧的宽度赋值给menu的宽度
            mContent.getLayoutParams().width = mScreenwidth;
            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed) {
            scrollTo(mMenuWidth, 0);
            isopen=false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {

            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();//获取的是当前scrollview 左上角的坐标
                if (scrollX >= mMenuWidth / 2) {
                    smoothScrollTo(mMenuWidth, 0);
                    isopen = false;
                } else {
                    smoothScrollTo(0, 0);
                    isopen = true;
                }

                return true;
        }

        return super.onTouchEvent(ev);
    }

    /**
     * 滑动状态发生变化后执行
     *
     * @param l
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        float scale = 1.0f * l / mMenuWidth;//滑动的比例 1-0
        ViewHelper.setTranslationX(mMenu, (float) (l * 0.8));


        /**
         * 区别1: 内容区域越来越小 比例的话 1.0-0.7   0.7+0.3*scal
         * 菜单区域越来越大,透明度越来越不透明  缩放 0.7-1   1.0-0.3*scal     ,透明度 0.6-1   0.6+0.4*(1.0f-scal)
         */

        float contentScale = 0.7f + 0.3f * scale;
        float menuScale = 1.0f - 0.3f * scale;
        float menuAlpha = 0.6f + 0.4f *(1.0f-scale);
        //菜单区域的缩放和透明度
        ViewHelper.setScaleX(mMenu, menuScale);
        ViewHelper.setScaleY(mMenu, menuScale);
        ViewHelper.setAlpha(mMenu, menuAlpha);


        //内容区域的缩放
        ViewHelper.setPivotX(mContent, 0);
        ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
        ViewHelper.setScaleX(mContent, contentScale);
        ViewHelper.setScaleY(mContent, contentScale);
    }

    /**
     * 打开菜单
     */
    public void open() {
        if (isopen) return;
        smoothScrollTo(0, 0);
        isopen = true;
    }

    /**
     * 关闭
     */
    public void close() {
        if (!isopen) return;
        smoothScrollTo(mMenuWidth, 0);
        isopen = false;
    }

    /**
     * 切换开关状态
     */
    public void toggle() {
        if (isopen) {
            close();
        } else {
            open();
        }
    }
}
