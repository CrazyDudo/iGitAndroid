package com.cd.igitandroid.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import com.cd.igitandroid.R;
import com.cd.igitandroid.data.db.DbOpenHelper;
import com.cd.igitandroid.ui.homepage.HomeActivity;
import com.cd.igitandroid.ui.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {

        if (isLogged()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }


    public boolean isLogged() {

        if (DbOpenHelper.getInstance().getmDaoSession().getAuthUserDao().loadAll().size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
