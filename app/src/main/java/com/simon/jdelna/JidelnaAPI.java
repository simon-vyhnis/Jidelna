package com.simon.jdelna;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JidelnaAPI {


    //https://www.jidelna.cz/rest/u/c58zbtfnjz72h6t5nzfva9uzvbag8m/zarizeni/177/dny/od/2021-11-10/do/2022-01-08
    @GET("/rest/u/c58zbtfnjz72h6t5nzfva9uzvbag8m/zarizeni/{eatery}/dny/od/{dateFrom}/do/{dateTo}")
    Call<List<DailyMenu>> getDailyMenus(@Path("eatery") String eatery, @Path("dateFrom") String dateFrom, @Path("dateTo") String dateTo);
}
