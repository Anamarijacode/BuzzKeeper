package com.opgkukic.buzzkeeper.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseUser;
import com.opgkukic.buzzkeeper.R;

public class LunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                if(isLoggedIn)
                {
                    Intent intent = new Intent(LunchActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                }
                else
                {
                Intent intent = new Intent(LunchActivity.this, MainActivity.class);
                startActivity(intent);
                finish();}
            }
        }, 2000);

    }

}