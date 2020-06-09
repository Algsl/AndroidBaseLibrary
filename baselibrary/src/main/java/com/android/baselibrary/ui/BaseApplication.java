package com.android.baselibrary.ui;

import android.app.Activity;
import android.app.Application;
import android.support.multidex.MultiDex;

import java.util.ArrayList;
import java.util.List;

public class BaseApplication extends Application {

    private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        MultiDex.install(this);
    }

    public static BaseApplication getApp(){
        return application;
    }

    private List<Activity> activities = new ArrayList<Activity>();

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for(Activity activity:activities){
            activity.finish();
        }
    }


}
