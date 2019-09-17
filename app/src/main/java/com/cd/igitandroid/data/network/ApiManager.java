package com.cd.igitandroid.data.network;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ruandong on 2019/9/11.
 */
public class ApiManager {


    private GithubApi mApiHelper;
    private TrendingApi mTrendingApi;
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

    public TrendingApi getTrendingDataService() {

        if (mTrendingApi == null) {
            synchronized (ApiManager.class) {
                if (mTrendingApi == null) {
                    mTrendingApi = new Retrofit.Builder()
                            .baseUrl(ApiEndPoint.GITHUB_TRENDING_URL)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(TrendingApi.class);
                }
            }
        }
        return mTrendingApi;
    }


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static OkHttpClient getClient(String username, String password) {
        String credentials = Credentials.basic(username, password);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", credentials)
                        .header("Accept", "application/json")
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });


        OkHttpClient client = httpClient.build();

        return client;

    }


    public GithubApi getLoginService(String user, String password) {
        if (mApiHelper == null) {
            synchronized (ApiManager.class) {

                if (mApiHelper == null) {
                    mApiHelper = new Retrofit.Builder()
                            .baseUrl(ApiEndPoint.GITHUB_API_BASE_URL)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(getClient(user, password))
                            .build()
                            .create(GithubApi.class);
                }
            }
        }
        return mApiHelper;
    }

}
