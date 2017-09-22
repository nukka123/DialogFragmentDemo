package com.example.dialogfragmentdemo.demo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;


public class MainApplication extends Application {

    @Override
    public void onCreate() {
        Timber.plant(new Timber.DebugTree());
        Timber.d("onCreate");
        super.onCreate();
        LeakCanary.install(this);
    }
}
