package com.simon.jdelna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.simon.jdelna.http.DailyMenu;
import com.simon.jdelna.http.HttpDao;
import com.simon.jdelna.http.User;

public class MainActivity extends AppCompatActivity {
    public static final String PREFERENCES_FILE = "com.simon.jidelna.LOGIN_INFO";
    TextView text;
    HttpDao http;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences(PREFERENCES_FILE,MODE_PRIVATE);
        if(!preferences.contains("email")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        text = findViewById(R.id.text);
        http = HttpDao.getInstance();

        http.login(preferences.getString("email", "err"), preferences.getString("password", "err")).observe(this, response->{
            if(response.isSuccessful()){
                StringBuilder builder = new StringBuilder();
                for(User user: response.getUsers()){
                    builder.append(user.getName()).append("\n");
                }
                text.setText(builder.toString());
                loadMenu();
            }else{
                text.setText("Přihlašování selhalo");
            }
        });



    }

    private void loadMenu(){
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