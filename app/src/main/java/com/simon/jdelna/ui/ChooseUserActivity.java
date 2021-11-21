package com.simon.jdelna.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simon.jdelna.R;
import com.simon.jdelna.http.HttpDao;
import com.simon.jdelna.http.User;

public class ChooseUserActivity extends AppCompatActivity {
    SharedPreferences preferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);

        HttpDao http = HttpDao.getInstance();
        preferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, MODE_PRIVATE);
        http.login(preferences.getString("email", "err"), preferences.getString("password", "err")).observe(this, response->{
            if(response.isSuccessful()){
                System.out.println("Response was successful");
                RecyclerView recyclerView = findViewById(R.id.user_list);
                UserListViewAdapter adapter = new UserListViewAdapter(response.getUsers(), this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }else{
                Toast.makeText(this,"Přihlašování selhalo",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onUserSelected(int userId){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("userid", userId);
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {}
}
