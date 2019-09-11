package com.cd.igitandroid.data.network;

import com.cd.igitandroid.data.network.model.TrendingBean;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ruandong on 2019/9/11.
 */
public class ApiManager {

    private GithubApi mApiHelper;
    private static ApiManager mApiManager;

    public static ApiManager getInstance() {

        if (mApiManager == null) {
            synchronized (ApiManager.class) {
                if (mApiManager == null) {
                    mApiManager = new ApiManager();
                }
            }
        }

        return mApiManager;
    }


    public GithubApi getApiHelper() {

        if (mApiHelper == null) {
            synchronized (ApiManager.class) {
                if (mApiHelper == null) {
                    mApiHelper = new Retrofit.Builder()
                            .baseUrl(ApiEndPoint.GITHUB_TRENDING_URL)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(GithubApi.class);

                }
            }
        }


        return mApiHelper;
    }
}
