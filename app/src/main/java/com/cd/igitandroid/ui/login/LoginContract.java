package com.cd.igitandroid.ui.login;

import com.cd.igitandroid.data.network.model.LoginResponseBean;
import com.cd.igitandroid.ui.base.BasePresenter;
import com.cd.igitandroid.ui.base.BaseView;

/**
 * Created by ruandong on 2019/9/16.
 */
public class LoginContract {

    interface Presenter extends BasePresenter {
        void login(String userName,String passwrod);
    }
    interface View extends BaseView<Presenter> {
        void onLoginSuccess(LoginResponseBean loginResponseBean);
        void onLoading();
        void onLoginError(String error);
    }


}
