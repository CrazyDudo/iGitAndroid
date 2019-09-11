package com.cd.igitandroid.ui.trending;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cd.igitandroid.R;
import com.cd.igitandroid.data.network.ApiManager;
import com.cd.igitandroid.data.network.model.TrendingBean;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingFragment extends Fragment {


    public TrendingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trending, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ApiManager.getInstance()
                .getApiHelper()
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
                    }

                    @Override
                    public void onError(Throwable e) {

                        Logger.e(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
