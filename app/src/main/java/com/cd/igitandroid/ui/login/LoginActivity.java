package com.cd.igitandroid.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cd.igitandroid.R;
import com.cd.igitandroid.data.network.model.LoginResponseBean;
import com.cd.igitandroid.di.component.DaggerActivityComponent;
import com.cd.igitandroid.di.module.ActivityModule;
import com.cd.igitandroid.ui.homepage.HomeActivity;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    @Inject
    LoginPresenter mPresenter;
    @BindView(R.id.edt_user_name)
    TextInputEditText edtUserName;
    @BindView(R.id.edt_password)
    TextInputEditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;


    private ProgressDialog mProgressDialog;


//    private LoginContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
//        new LoginPresenter(this);
        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule())
                .build()
                .inject(this);
        initView();
    }

    private void initView() {
        mPresenter.takeView(this);
        mPresenter.checkLoginStatus();
    }

    @Override
    public void onCheckLoginResult(boolean isLogged) {
        if (isLogged) {
            startHomepage();
        }
    }

    @Override
    public void onLoginSuccess(LoginResponseBean loginResponseBean) {
        mProgressDialog.dismiss();
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        startHomepage();

    }

    public void startHomepage() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void onLoading() {
        mProgressDialog = ProgressDialog.show(this, null, "Login...");

    }

    @Override
    public void onLoginError(String error) {
        mProgressDialog.dismiss();
        Toast.makeText(this, "onLoginError:" + error, Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.edt_user_name, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_login:
                mPresenter.login(edtUserName.getText().toString().trim(), edtPassword.getText().toString().trim());
                break;
        }
    }
}
