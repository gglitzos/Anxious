package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DELAY = 2500; //time in milliseconds to "splash" onto the first screen before going to the main activity

    //Widgets for splash
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setBackgroundDrawable(null);

        //call method
        initializeView();
        animateLogo();
        goToMainActivity();

    }

    private void initializeView() {
        imageView = findViewById(R.id.imageView);
    }

    private void animateLogo() {
        //this method is used to animate the logo
        Animation fadingInAnimation = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        fadingInAnimation.setDuration(SPLASH_DELAY);

        imageView.startAnimation(fadingInAnimation);
    }

    private void goToMainActivity() {
        // this till take the user to the main activity after the splash screen
        new Handler().postDelayed(()-> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, SPLASH_DELAY);

        }

}
