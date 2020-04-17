package com.example.customtablayoutdemo;

import android.app.Application;
import android.content.Context;

/**
 * @author : zhiwen.yang
 * date   : 2020/4/14
 * desc   :
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
