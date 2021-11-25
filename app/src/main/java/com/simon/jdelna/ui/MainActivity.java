package com.simon.jdelna.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.simon.jdelna.R;
import com.simon.jdelna.http.HttpDao;
import com.simon.jdelna.http.model.DayPart;

public class MainActivity extends AppCompatActivity {
    public static final String PREFERENCES_FILE = "com.simon.jidelna.LOGIN_INFO";
    RecyclerView days;
    HttpDao http;
    SharedPreferences preferences;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(PREFERENCES_FILE,MODE_PRIVATE);
        if(!preferences.contains("email")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        userId = preferences.getInt("userid",0);

        days = findViewById(R.id.food_list);
        http = HttpDao.getInstance();

        http.login(preferences.getString("email", "err"), preferences.getString("password", "err")).observe(this, response->{
            if(response.isSuccessful()){
                loadMenu();
            }else{
                Toast.makeText(this, "Přihlašování selhalo", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.post).setOnClickListener((v)->{
            //TODO: post updates here
        });

    }

    private void loadMenu(){
        http.getMenus().observe(this, dayWraps -> {
            findViewById(R.id.loading).setVisibility(View.GONE);
            DaysViewAdapter adapter = new DaysViewAdapter(dayWraps, this);
            days.setAdapter(adapter);
            days.setLayoutManager(new LinearLayoutManager(this));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.logout){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.switch_user){
            Intent intent = new Intent(this, ChooseUserActivity.class);
            startActivity(intent);
        }
        return(super.onOptionsItemSelected(item));
    }


    @Override
    public void onBackPressed() {}

    public int getUserId(){
        return userId;
    }

    public void addOrderChange(DayPart dayPart){

    }

}