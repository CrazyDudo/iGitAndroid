package com.cd.igitandroid.ui.issue;


import com.cd.igitandroid.data.db.DbOpenHelper;
import com.cd.igitandroid.data.db.entity.AuthUser;
import com.cd.igitandroid.data.network.ApiManager;
import com.cd.igitandroid.data.network.model.IssueSearchResultBean;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ruandong on 2019/9/16.
 */
public class IssuePresenter implements IssueContract.Presenter {


    private IssueContract.View mView;


    @Override
    public void dropView() {

    }


    @Override
    public void loadData(int page) {
        mView.onLoading();

        AuthUser lastAuth = DbOpenHelper.getInstance().getLastAuth();
//    https://api.github.com/search/issues?sort=created&page=1&q=user:ThirtyDegreesRay+state:open&order=desc
//        "user:crazydudo+state:open"
        ApiManager.getInstance()
                .getSerchIssueService()
                .searchIssues(false, getQuery(lastAuth), "created", "desc", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IssueSearchResultBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IssueSearchResultBean searchResultBeanResponse) {
                        Logger.d(searchResultBeanResponse);

                        if (searchResultBeanResponse.getTotal_count()<=0||searchResultBeanResponse==null) {
                            mView.onEmpty();
                        } else {
                            mView.onSuccess(searchResultBeanResponse);
                        }
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

    private String getQuery(AuthUser authUser) {

        return "user:" + authUser.getLoginId() + "+state:open";
    }

    @Override
    public void takeView(IssueContract.View view) {

        mView = view;
    }
}
