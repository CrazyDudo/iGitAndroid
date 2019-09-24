package com.cd.igitandroid.ui.news;

import com.cd.igitandroid.data.network.ApiManager;
import com.cd.igitandroid.data.network.model.EventBean;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by ruandong on 2019/9/12.
 */
public class NewsPresenter implements NewsContract.Presenter {

    private NewsContract.View mView;


    @Override
    public void requestEventData(int page) {
        mView.onLoading();
        ApiManager.getInstance().getUserService()
                .getNewsEvent(true, "crazydudo", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ArrayList<EventBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<ArrayList<EventBean>> arrayListResponse) {


                        mView.onSuccess(arrayListResponse.body());
                        Logger.d(arrayListResponse);



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
    public void takeView(NewsContract.View view) {

        mView = view;
    }


    @Override
    public void dropView() {

    }
}
