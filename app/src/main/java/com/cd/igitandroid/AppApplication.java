package com.cd.igitandroid;

import android.app.Application;

import com.cd.igitandroid.data.db.DbOpenHelper;
import com.cd.igitandroid.data.db.entity.AuthUser;
import com.cd.igitandroid.data.db.gen.DaoMaster;
import com.cd.igitandroid.data.db.gen.DaoSession;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by ruandong on 2019/9/11.
 */
public class AppApplication extends Application {
    private static Application mApplication;
    private DaoSession mDaoSession;


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

        mDaoSession =
                new DaoMaster(new DbOpenHelper(this, "auth_user.db").getWritableDb()).newSession();

        // USER CREATION FOR DEMO PURPOSE
        if (mDaoSession.getAuthUserDao().loadAll().size() == 0) {
            mDaoSession.getAuthUserDao().insert(new AuthUser());
        }
    }

    public static Application getContext() {


        return mApplication;
    }


    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
