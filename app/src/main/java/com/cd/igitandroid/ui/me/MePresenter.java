package com.cd.igitandroid.ui.me;

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

        // TODO: 2019/9/25 数据库查询登录信息
        String basic = "Basic Y3JhenlkdWRvOmNkMTAwNDQyMjU=";
        Logger.d(basic);
        ApiManager.getInstance()
                .getLoginService(basic)
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

    @Override
    public void takeView(MeContract.View view) {

        mView = view;
    }

    @Override
    public void dropView() {

    }
}
