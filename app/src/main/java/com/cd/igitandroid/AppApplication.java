package com.cd.igitandroid;

import android.app.Application;

import com.cd.igitandroid.data.db.DbOpenHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by ruandong on 2019/9/11.
 */
public class AppApplication extends Application {
    private static Application mApplication;



    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        init();
    }

    private void init() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        initDatabase();
    }

    private void initDatabase() {
        DbOpenHelper.getInstance();
    }

    public static Application getContext() {


        return mApplication;
    }



}
