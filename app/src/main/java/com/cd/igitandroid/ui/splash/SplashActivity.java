package com.cd.igitandroid.ui.splash;

import android.os.Bundle;

import com.cd.igitandroid.AppApplication;
import com.cd.igitandroid.R;
import com.cd.igitandroid.data.db.entity.AuthUser;
import com.cd.igitandroid.data.db.gen.AuthUserDao;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private AuthUserDao mAuthUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuthUserDao = ((AppApplication) getApplication()).getDaoSession().getAuthUserDao();
        //        User user = ((DemoApp)getApplication()).getDaoSession().getUserDao().load(1L);
        AuthUser authUser = new AuthUser();
        Random random = new Random();
        authUser.setId(random.nextLong());
        authUser.setLoginId("testuser1");


        mAuthUserDao.insert(authUser);




        mAuthUserDao.detachAll();
        List<AuthUser> userList = mAuthUserDao.loadAll();
//                Collections.reverse(userList);
//                Logger.d(userList.get(0).getId());

        QueryBuilder<AuthUser> qb = mAuthUserDao.queryBuilder().orderDesc().limit(1);    //invert list
        AuthUser unique = qb.build().unique();

         Logger.d(unique.getId());
         Logger.d(userList);


    }
}
