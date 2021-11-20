package com.simon.jdelna.http;

import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.EOFException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpDao {

    private static volatile HttpDao instance;
    private final Retrofit retrofit;
    private String cookie;
    private String eatery = "";

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

    private final MutableLiveData<List<DailyMenu>> menus = new MutableLiveData<>();
    public LiveData<List<DailyMenu>> getMenus(){
        JidelnaAPI api = retrofit.create(JidelnaAPI.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Call<List<DailyMenu>> call = api.getDailyMenus(eatery,
                "2021-11-21","2022-11-21");
        /*dateFormat.format(System.currentTimeMillis()),
                dateFormat.format(System.currentTimeMillis()+ 365L*24*60*60*1000)*/
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

    private final MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();
    public LiveData<LoginResponse> login(String email, String password){
        Retrofit retrofitString = new Retrofit.Builder()
                .baseUrl("https://www.jidelna.cz/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        JidelnaAPI api = retrofitString.create(JidelnaAPI.class);
        Call<String> call = api.masterLogin(email, password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    //saving login cookie
                    cookie = response.headers().values("Set-Cookie").get(1).split(";")[0];
                    //saving eatery num
                    final Pattern patternEatery = Pattern.compile("\"regc\":\"([0-9]*)\",");
                    final Matcher matcherEatery = patternEatery.matcher(response.body());
                    while(matcherEatery.find()){
                        eatery = matcherEatery.group(1);
                    }
                    //getting userIds
                    Thread thread = new Thread(()->{
                        List<Integer> userIds = new ArrayList<>();
                        final Pattern pattern = Pattern.compile("\"id\":([0-9]*),");
                        final Matcher matcher = pattern.matcher(response.body());
                        while(matcher.find()){
                            userIds.add(Integer.parseInt(matcher.group(1)));
                        }
                        List<User> users = new ArrayList<>();
                        for (int i = 0; i < userIds.size(); i++) {
                            try {
                                users.add(getUserInfo(userIds.get(i)));
                            } catch (Exception e) {
                                loginResponse.postValue(new LoginResponse(false));
                                e.printStackTrace();
                                return;
                            }
                        }
                        loginResponse.postValue(new LoginResponse(users));
                    });
                    thread.start();
                }else{
                    loginResponse.postValue(new LoginResponse(false));
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                loginResponse.postValue(new LoginResponse(false));
                t.printStackTrace();
            }
        });
        return loginResponse;
    }

    private User getUserInfo(int userId) throws Exception {
        JidelnaAPI api = retrofit.create(JidelnaAPI.class);
        Call<User> call = api.getUserInfo(userId, cookie);
        Response<User> response = call.execute();
        if(response.isSuccessful()){
            User user = response.body();
            user.setUserId(userId);
            return user;
        }
        System.out.println(response.errorBody().string());
        System.out.println("Cookie: "+cookie+", UserId: "+userId);
        throw new Exception("Can't get user");
    }
}

