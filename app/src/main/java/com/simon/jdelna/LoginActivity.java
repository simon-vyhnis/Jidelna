package com.simon.jdelna;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.inputEmail);
        EditText password = findViewById(R.id.inputPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        SharedPreferences preferences = getSharedPreferences(MainActivity.PREFERENCES_FILE, MODE_PRIVATE);

        buttonLogin.setOnClickListener(v->{
            if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                Toast.makeText(this, getString(R.string.missing_info), Toast.LENGTH_SHORT).show();
            }else{
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("email", email.getText().toString());
                editor.putString("password", password.getText().toString());
                editor.commit();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
