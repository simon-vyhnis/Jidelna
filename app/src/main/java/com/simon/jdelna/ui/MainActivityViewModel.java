package com.simon.jdelna.ui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.simon.jdelna.http.HttpDao;
import com.simon.jdelna.http.LoginResponse;
import com.simon.jdelna.http.model.DayPart;
import com.simon.jdelna.http.model.DayWrap;
import com.simon.jdelna.http.model.OrderRequest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MainActivityViewModel extends AndroidViewModel {
    private final int userId;
    private ArrayList<OrderRequest> requests;
    private final HttpDao http;
    SharedPreferences preferences;

    public MainActivityViewModel(Application application) {
        super(application);
        http = HttpDao.getInstance();
        preferences = application.getSharedPreferences(MainActivity.PREFERENCES_FILE, Context.MODE_PRIVATE);
        userId = preferences.getInt("userid",0);
    }

    public LiveData<LoginResponse> login(){
        return http.login(preferences.getString("email", "err"), preferences.getString("password", "err"));
    }
    public LiveData<List<DayWrap>> getMenus(){
        return http.getMenus(String.valueOf(userId));
    }
    public int getUserId(){
        return userId;
    }
    public boolean isLoggedIn(){
        return !preferences.contains("email");
    }

    public void addOrderChange(DayPart dayPart, String date){
        if(requests == null){
            requests = new ArrayList<>();
        }else {
            Iterator<OrderRequest> iterator = requests.iterator();
            while(iterator.hasNext()){
                if(iterator.next().getDayPart() == dayPart){
                    iterator.remove();
                }
            }
        }
        requests.add(new OrderRequest(Integer.toString(userId), date, dayPart));
    }

    public ArrayList<OrderRequest> getRequests(){
        return requests;
    }
}
