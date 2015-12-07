package com.example.jackiechan.myannotiactiondemo;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jackiechan on 15/12/7.
 */
public class ViewInjectIml {
    private static ViewInjectIml viewInjectIml;
    private static Object lock = new Object();

    private ViewInjectIml() {

    }

    /**
     * 返回一个具体的处理类对象
     * @return
     */
    public static ViewInjectIml getinstance() {
        if (viewInjectIml == null) {
            synchronized (lock) {
                if (viewInjectIml == null) {
                    viewInjectIml = new ViewInjectIml();
                }
            }
        }
        return viewInjectIml;
    }

    /**
     * 初始化注解
     * 由于 setcontentview 也是通过注解设置的,而我们 findviewbyid 之前必须有 contentview.所以首先应该反射获取 setcontentview 的注解
     * 2 获取资源 id 后 需要设置到 activity 上面，怎么设置呢，通过反射获取到 setontentview 的方法 然后调用
     * @param activity
     */
    public void inject(Activity activity) {
        Class clazz = activity.getClass();//获取到ACTIIVYT的 class
        SetContentView setContentView = findContenView(clazz);//获取 contentview 的值
        int value = setContentView.value();//获取资源的 id
        try {
            Method setContentViewMethod = clazz.getMethod("setContentView", int.class);//获取当前类里面参数类型为 int 的名字叫setContentView的方法
            //给 activiyt 设置 contentview, 通过反射调用设置方法
            setContentViewMethod.invoke(activity, value);//反射调用方法,第一个参数,要调用哪个对象身上的这个方法,第二参数,调用这个方法的参数的值什么
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        findView(activity,clazz);
    }

    /**
     *开始反射控件的注解
     * @param activity
     * @param clazz
     */
    private void findView(Activity activity, Class clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();//首先获取所有的字段
        for (Field declaredField : declaredFields) {
            //在这里应该判断变量是否是 view, 是否是 final, 是否是静态,不符合条件的不应该去获取注解
            ViewInect annotation = declaredField.getAnnotation(ViewInect.class);//获取变量身上的ViewInect注解
            if (annotation != null) {
                int id = annotation.value();//获取注解指定的值
                View view = activity.findViewById(id);//通过 activity 的 findviewById 方法找到对应的 view
                declaredField.setAccessible(true);//运行访问
                try {
                    declaredField.set(activity, view);//给 view 设置值,第一个参数,当前变量所在的具体activity, 第二个参数 它的值
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    /**
     * 用于获取 activity 上面关于setcontentview的注解
     * @param clazz
     * @return
     */
    private SetContentView findContenView(Class clazz) {
        if (clazz == null) {
            return null;
        }
        //获取当前类身上的注解，返回对应的对象，对象里面封装有 setcontentview 的资源 id
        SetContentView SetContentView = (com.example.jackiechan.myannotiactiondemo.SetContentView) clazz.getAnnotation(com.example.jackiechan.myannotiactiondemo.SetContentView.class);
        if (SetContentView == null) {
            return findContenView(clazz.getSuperclass());//通过父类身上获取，
        }
        return SetContentView;
    }
}
