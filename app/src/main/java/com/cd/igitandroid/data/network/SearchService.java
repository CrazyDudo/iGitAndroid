package com.cd.igitandroid.data.network;

import com.cd.igitandroid.data.network.model.IssueSearchResultBean;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by ruandong on 2019/9/23.
 */
public interface SearchService {
    //    https://api.github.com/search/issues?sort=created&page=1&q=user:ThirtyDegreesRay+state:open&order=desc
    @NonNull @GET("search/issues")
    @Headers("Accept: application/vnd.github.html,application/vnd.github.VERSION.raw")
    Observable<Response<IssueSearchResultBean>> searchIssues(
            @Header("forceNetWork") boolean forceNetWork,
            @Query(value = "q", encoded = true) String query,
            @Query("sort") String sort,
            @Query("order") String order,
            @Query("page") int page
    );

}
