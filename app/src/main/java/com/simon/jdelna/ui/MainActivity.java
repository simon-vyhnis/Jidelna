package com.simon.jdelna.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.simon.jdelna.R;
import com.simon.jdelna.http.HttpDao;
import com.simon.jdelna.http.model.DayPart;
import com.simon.jdelna.http.model.OrderRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String PREFERENCES_FILE = "com.simon.jidelna.LOGIN_INFO";
    private RecyclerView days;
    private ImageButton btnPost;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        if(viewModel.isLoggedIn()){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        days = findViewById(R.id.food_list);

        viewModel.login().observe(this, response->{
            if(response.isSuccessful()){
                loadMenu();
            }else{
                Toast.makeText(this, "Přihlašování selhalo", Toast.LENGTH_SHORT).show();
            }
        });

        btnPost = findViewById(R.id.post);
        btnPost.setOnClickListener((v)->{
            Intent intent = new Intent(this, PostChangesActivity.class);
            intent.putExtra("requests", (Serializable) viewModel.getRequests());
            startActivity(intent);
        });

    }

    private void loadMenu(){
        viewModel.getMenus().observe(this, dayWraps -> {
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

    public void addOrderChange(DayPart dayPart, String date) {
        btnPost.setVisibility(View.VISIBLE);
        viewModel.addOrderChange(dayPart, date);
    }

    public int getUserId(){
        return viewModel.getUserId();
    }

}