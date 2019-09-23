package com.cd.igitandroid.ui.issue;


import com.cd.igitandroid.data.network.ApiManager;
import com.cd.igitandroid.data.network.model.IssueSearchResultBean;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

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
//    https://api.github.com/search/issues?sort=created&page=1&q=user:ThirtyDegreesRay+state:open&order=desc
        ApiManager.getInstance()
                .getSerchIssueService()
                .searchIssues(false, "user:crazydudo+state:open", "created", "desc", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<IssueSearchResultBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<IssueSearchResultBean> searchResultBeanResponse) {

                        Logger.d(searchResultBeanResponse);
                        mView.onSuccess(searchResultBeanResponse.body());
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
    public void takeView(IssueContract.View view) {

        mView = view;
    }
}
