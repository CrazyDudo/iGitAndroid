package com.cd.igitandroid.ui.login;

import com.cd.igitandroid.data.db.DbOpenHelper;
import com.cd.igitandroid.data.db.entity.AuthUser;
import com.cd.igitandroid.data.network.ApiManager;
import com.cd.igitandroid.data.network.model.LoginResponseBean;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Credentials;

/**
 * Created by ruandong on 2019/9/16.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    @Override
    public void checkLoginStatus() {
        if (DbOpenHelper.getInstance().getmDaoSession().getAuthUserDao().loadAll().size() > 0) {
            mView.onCheckLoginResult(true);
        } else {
            mView.onCheckLoginResult(false);
        }
    }

    @Override
    public void login(String userName, String password) {
        mView.onLoading();
        if (userName.isEmpty() || password.isEmpty()) {
            mView.onLoginError("user name or password cannot be empty");
            return;
        }

        String basic = Credentials.basic(userName, password);

        ApiManager.getInstance()
                .getLoginService(basic)
                .login()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResponseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResponseBean loginResponseBean) {

                        Logger.d(loginResponseBean);
                        mView.onLoginSuccess(loginResponseBean);

                        saveToken(basic, loginResponseBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.getMessage());
                        mView.onLoginError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void saveToken(String token, LoginResponseBean loginResponseBean) {


        AuthUser authUser = new AuthUser();

//        authUser.setId(1l);
        authUser.setAccessToken(token);
        authUser.setLoginId(loginResponseBean.getLogin());
        authUser.setAvatar(loginResponseBean.getAvatar_url());
        authUser.setAuthTime(System.currentTimeMillis() + "");
        DbOpenHelper.getInstance().getmDaoSession().getAuthUserDao().insert(authUser);
    }


    @Override
    public void takeView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void dropView() {

    }
}
