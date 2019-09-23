package com.cd.igitandroid.data.network;

import java.io.IOException;

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


    private LoginService mLoginService;
    private TrendingService mTrendingService;
    private UserService mUserService;
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


    public UserService getUserService() {

        if (mUserService == null) {
            synchronized (ApiManager.class) {
                if (mUserService == null) {
                    mUserService = new Retrofit.Builder()
                            .baseUrl(ApiEndPoint.GITHUB_API_BASE_URL)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(UserService.class);

                }
            }
        }

        return mUserService;
    }


    public TrendingService getTrendingDataService() {

        if (mTrendingService == null) {
            synchronized (ApiManager.class) {
                if (mTrendingService == null) {
                    mTrendingService = new Retrofit.Builder()
                            .baseUrl(ApiEndPoint.GITHUB_TRENDING_URL)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(TrendingService.class);
                }
            }
        }
        return mTrendingService;
    }


    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static OkHttpClient getClient(String credentials) {

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


    public LoginService getLoginService(String credentials) {
        if (mLoginService == null) {
            synchronized (ApiManager.class) {

                if (mLoginService == null) {
                    mLoginService = new Retrofit.Builder()
                            .baseUrl(ApiEndPoint.GITHUB_API_BASE_URL)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(getClient(credentials))
                            .build()
                            .create(LoginService.class);
                }
            }
        }
        return mLoginService;
    }


    private SearchService mSearchIssueService;

    //search issue
    public SearchService getSerchIssueService() {

        if (mSearchIssueService == null) {
            synchronized (ApiManager.class) {
                if (mSearchIssueService == null) {
                    mSearchIssueService = new Retrofit.Builder()
                            .baseUrl(ApiEndPoint.GITHUB_API_BASE_URL)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(SearchService.class);
                }
            }
        }
        return mSearchIssueService;
    }
}
