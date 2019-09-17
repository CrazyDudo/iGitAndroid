package com.cd.igitandroid.data.network;

import com.cd.igitandroid.data.network.model.LoginResponseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ruandong on 2019/9/11.
 */
public interface GithubApi {
    @GET("/user")
    Observable<LoginResponseBean> login();
}
