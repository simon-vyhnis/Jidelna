package com.simon.jdelna.http;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpDao {

    private static volatile HttpDao instance;
    private final Retrofit retrofit;

    private HttpDao(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.jidelna.cz/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static HttpDao getInstance() {
        if(instance==null){
            instance=new HttpDao();
        }
        return instance;
    }

    private MutableLiveData<List<DailyMenu>> menus = new MutableLiveData<>();
    public LiveData<List<DailyMenu>> getMenus(){
        JidelnaAPI api = retrofit.create(JidelnaAPI.class);
        Call<List<DailyMenu>> call = api.getDailyMenus("177","2021-11-10","2022-01-08");
        System.out.println(call.request().url().toString());
        call.enqueue(new Callback<List<DailyMenu>>() {
            @Override
            public void onResponse(Call<List<DailyMenu>> call, Response<List<DailyMenu>> response) {
                if(response.isSuccessful()){
                    menus.postValue(response.body());
                }else{
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DailyMenu>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return menus;
    }

    public void masterLogin(String email, String password){
        Retrofit retrofitString = new Retrofit.Builder()
                .baseUrl("https://www.jidelna.cz/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        JidelnaAPI api = retrofitString.create(JidelnaAPI.class);
        Call<String> call = api.masterLogin(email, password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}

