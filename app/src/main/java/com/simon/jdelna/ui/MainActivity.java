package com.simon.jdelna.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.simon.jdelna.R;
import com.simon.jdelna.http.DailyMenu;
import com.simon.jdelna.http.HttpDao;
import com.simon.jdelna.http.User;
import com.simon.jdelna.ui.LoginActivity;

public class MainActivity extends AppCompatActivity {
    public static final String PREFERENCES_FILE = "com.simon.jidelna.LOGIN_INFO";
    TextView text;
    HttpDao http;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(PREFERENCES_FILE,MODE_PRIVATE);
        if(!preferences.contains("email")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        text = findViewById(R.id.text);
        http = HttpDao.getInstance();

        http.login(preferences.getString("email", "err"), preferences.getString("password", "err")).observe(this, response->{
            if(response.isSuccessful()){
                loadMenu();
            }else{
                Toast.makeText(this, "Přihlašování selhalo", Toast.LENGTH_SHORT).show();
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
}