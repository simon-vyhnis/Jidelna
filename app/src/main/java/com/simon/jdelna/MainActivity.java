package com.simon.jdelna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.simon.jdelna.http.DailyMenu;
import com.simon.jdelna.http.HttpDao;

public class MainActivity extends AppCompatActivity {
    public static final String PREFERENCES_FILE = "com.simon.jidelna.LOGIN_INFO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences(PREFERENCES_FILE,MODE_PRIVATE);
        if(!preferences.contains("email")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        TextView text = findViewById(R.id.text);
        HttpDao http = HttpDao.getInstance();

        http.masterLogin(preferences.getString("email", "err"), preferences.getString("password", "err"));

        http.getMenus().observe(this, dailyMenus -> {
            StringBuilder textBuilder = new StringBuilder();
            for(DailyMenu menu : dailyMenus){
               textBuilder.append(menu.getDate()).append("\n");
               for(DailyMenu.Food food : menu.getFoods()){
                   textBuilder.append(" ").append(food.getName()).append("\n");
                   for(DailyMenu.Food.Course course : food.getCourses()){
                       textBuilder.append(" ").append(course.getTitle()).append(": ").append(course.getContent()).append("\n");
                   }
               }
            }
            text.setText(textBuilder.toString());
        });
    }

    @Override
    public void onBackPressed() {}
}