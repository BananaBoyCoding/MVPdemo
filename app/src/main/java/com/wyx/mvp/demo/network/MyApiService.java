package com.wyx.mvp.demo.network;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * author   : BananaBoy
 * edition  : 1.0
 * time     : 2019/5/24
 * describe :
 */
public interface MyApiService {

    @GET
    Observable<ResponseBody> get(@Url String url, @HeaderMap Map<String, Object> headMap, @QueryMap Map<String, Object> queryMap);

    @POST
    Observable<ResponseBody> post(@Url String url, @HeaderMap Map<String, Object> headMap, @QueryMap Map<String, Object> queryMap);

    @PUT
    Observable<ResponseBody> put(@Url String url, @HeaderMap Map<String, Object> headMap, @QueryMap Map<String, Object> queryMap);

    @DELETE
    Observable<ResponseBody> delete(@Url String url, @HeaderMap Map<String, Object> headMap, @QueryMap Map<String, Object> queryMap);

    @POST
    Observable<ResponseBody> image(@Url String url, @HeaderMap Map<String, Object> headermap, @Body MultipartBody body);


}
