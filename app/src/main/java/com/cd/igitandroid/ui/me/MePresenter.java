package com.cd.igitandroid.ui.me;

import com.cd.igitandroid.data.db.DbOpenHelper;
import com.cd.igitandroid.data.db.entity.AuthUser;
import com.cd.igitandroid.data.db.entity.LocalUser;
import com.cd.igitandroid.data.network.ApiManager;
import com.cd.igitandroid.data.network.model.UserBean;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ruandong on 2019/9/25.
 */
public class MePresenter implements MeContract.Presenter {

    private MeContract.View mView;

    @Override
    public void loadData() {

        if (DbOpenHelper.getInstance().getLastLocalUser() != null) {
            loadLocalData();
        } else {
            loadHostData();
        }

    }

    private void loadHostData() {
        AuthUser lastAuth = DbOpenHelper.getInstance().getLastAuth();
//        String basic = "Basic Y3JhenlkdWRvOmNkMTAwNDQyMjU=";
//        Logger.d(basic);
        ApiManager.getInstance()
                .getLoginService(lastAuth.getAccessToken())
                .loginUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        Logger.d(userBean);
                        mView.onSuccess(userBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Logger.e(e.getMessage());
                        mView.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void loadLocalData() {
        LocalUser lastLocalUser = DbOpenHelper.getInstance().getLastLocalUser();
        UserBean userBean = new UserBean();
        userBean.setLogin(lastLocalUser.getLogin());
        userBean.setAvatar_url(lastLocalUser.getAvatarUrl());
        userBean.setName(lastLocalUser.getName());
        userBean.setFollowers(lastLocalUser.getFollowers());
        userBean.setFollowing(lastLocalUser.getFollowing());
        userBean.setPublic_repos(lastLocalUser.getRepos());

        mView.onSuccess(userBean);


    }

    @Override
    public void takeView(MeContract.View view) {

        mView = view;
    }

    @Override
    public void dropView() {

    }
}
