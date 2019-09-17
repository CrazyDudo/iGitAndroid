package com.cd.igitandroid.data.network;

import com.cd.igitandroid.data.network.model.TrendingBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ruandong on 2019/9/11.
 */
public interface TrendingApi {
    @GET("/")
    Observable<List<TrendingBean>> getTrendingData();

}
