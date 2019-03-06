package com.example.administrator.cachedome;

import android.app.Application;

import com.wzgiceman.rxretrofitlibrary.retrofit_rx.RxRetrofitApp;

/**
 * Created by Administrator on 2019/3/6.
 */

public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        RxRetrofitApp.init(this);
    }
}
