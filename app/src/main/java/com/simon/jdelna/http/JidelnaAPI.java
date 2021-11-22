package com.simon.jdelna.http;

import com.simon.jdelna.http.model.DayWrap;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JidelnaAPI {


    //https://www.jidelna.cz/rest/u/c58zbtfnjz72h6t5nzfva9uzvbag8m/zarizeni/177/dny/od/2021-11-10/do/2022-01-08
    @GET("/rest/u/c58zbtfnjz72h6t5nzfva9uzvbag8m/zarizeni/{eatery}/dny/od/{dateFrom}/do/{dateTo}")
    Call<List<DayWrap>> getDayWraps(@Path("eatery") String eatery, @Path("dateFrom") String dateFrom, @Path("dateTo") String dateTo);

    @FormUrlEncoded
    @POST("/rest/u/c58zbtfnjz72h6t5nzfva9uzvbag8m/login/jmenoheslo")
    Call<String> masterLogin(@Field("login") String email, @Field("heslo") String password);

    @GET("/rest/u/c58zbtfnjz72h6t5nzfva9uzvbag8m/uzivatel/{userId}/info")
    Call<User> getUserInfo(@Path("userId") int userId, @Header("Cookie") String cookie);

}
