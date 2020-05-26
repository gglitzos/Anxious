package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000; //time in milliseconds to "splash" onto the first screen before going to the main activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent (SplashActivity.this, MainActivity.class); //SplashActivity will be before the MainActivity
                startActivity(splashIntent);
                finish();
            }
        },SPLASH_TIME_OUT);//finish with 3 second timeout
    }
}
