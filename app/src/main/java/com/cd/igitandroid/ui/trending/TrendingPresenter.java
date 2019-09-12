package com.cd.igitandroid.ui.trending;

import com.cd.igitandroid.data.network.ApiManager;
import com.cd.igitandroid.data.network.model.TrendingBean;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

/**
 * Created by ruandong on 2019/9/12.
 */
public class TrendingPresenter implements TrendingContract.Presenter {

    private TrendingContract.View mView;


    public TrendingPresenter(TrendingContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void requestData() {
        mView.onLoading();
        ApiManager.getInstance()
                .getTrendingDataService()
                .getTrendingData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subject<List<TrendingBean>>() {
                    @Override
                    protected void subscribeActual(Observer<? super List<TrendingBean>> observer) {

                    }

                    @Override
                    public boolean hasObservers() {
                        return false;
                    }

                    @Override
                    public boolean hasThrowable() {
                        return false;
                    }

                    @Override
                    public boolean hasComplete() {
                        return false;
                    }

                    @Override
                    public Throwable getThrowable() {
                        return null;
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<TrendingBean> trendingBeans) {
                        Logger.d(trendingBeans);

                        mView.onRequestSuccess(trendingBeans);

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onError(e.getMessage());
                        Logger.e(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void start() {

    }
}
