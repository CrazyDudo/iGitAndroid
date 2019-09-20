package com.cd.igitandroid.data.network;

import com.cd.igitandroid.data.network.model.EventBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ruandong on 2019/9/20.
 */
public interface UserService {
    @NonNull
    @GET("users/{user}/received_events")
    Observable<Response<ArrayList<EventBean>>> getNewsEvent(
            @Header("forceNetWork") boolean forceNetWork,
            @Path("user") String user,
            @Query("page") int page
    );
}
