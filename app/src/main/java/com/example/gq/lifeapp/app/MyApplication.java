package com.example.gq.lifeapp.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by gq on 2015/8/16.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
