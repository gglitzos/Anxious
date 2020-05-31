package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {



    ImageButton link;
    ImageButton diary;
    ImageButton youtubebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youtubebtn = findViewById(R.id.youtubebtn);
        youtubebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openYouTubeMain();
            }
        });

        link = findViewById(R.id.ulink);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLinks();
            }
        });

        diary = findViewById(R.id.diary);
        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiary();
            }
        });
    }


    public void openLinks(){
        Intent intent = new Intent(this, Links.class);
        startActivity(intent);
    }

    public void openDiary(){
        Intent intent = new Intent(this, JournalMain.class);
        startActivity(intent);
    }

    public void openYouTubeMain() {
        Intent intent = new Intent( this, YouTubeMain.class);
        startActivity(intent);
    }
}
