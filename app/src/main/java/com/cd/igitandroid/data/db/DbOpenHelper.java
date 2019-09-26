package com.cd.igitandroid.data.db;


import com.cd.igitandroid.AppApplication;
import com.cd.igitandroid.data.db.entity.AuthUser;
import com.cd.igitandroid.data.db.gen.AuthUserDao;
import com.cd.igitandroid.data.db.gen.DaoMaster;
import com.cd.igitandroid.data.db.gen.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;


public class DbOpenHelper {

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DbOpenHelper() {
        init();
    }

    /**
     * 静态内部类，实例化对象使用
     */
    private static class SingleInstanceHolder {
        private static final DbOpenHelper INSTANCE = new DbOpenHelper();
    }

    /**
     * 对外唯一实例的接口
     *
     * @return
     */
    public static DbOpenHelper getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    /**
     * 初始化数据
     */
    private void init() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(AppApplication.getContext(),
                "IGit.db");
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoMaster getmDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        mDaoSession = mDaoMaster.newSession();
        return mDaoSession;
    }

    /**
     * Get Last auth user
     *
     * @return Entity
     */
    public AuthUser getLastAuth() {
        if (mDaoSession == null) {
            init();
        }
        mDaoSession.getAuthUserDao().detachAll();
        QueryBuilder<AuthUser> qb = mDaoSession.getAuthUserDao().queryBuilder().orderDesc(AuthUserDao.Properties.Id).limit(1);    //invert list
        return qb.build().unique();
    }


}

