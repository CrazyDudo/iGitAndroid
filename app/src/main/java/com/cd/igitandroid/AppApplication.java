package com.cd.igitandroid;

import android.app.Application;

/**
 * Created by ruandong on 2019/9/11.
 */
public class AppApplication extends Application {
    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static Application getContext() {
        return mApplication;
    }
}
