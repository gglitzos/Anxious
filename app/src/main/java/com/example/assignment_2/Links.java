package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Links extends AppCompatActivity {

    //declare image buttons
    ImageButton ata;
    ImageButton bdi;
    ImageButton bblue;
    ImageButton lifel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);

        lifel = findViewById(R.id.lifel);
        lifel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlifel();
            }
        });

        bblue = findViewById(R.id.bblue);
        bblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbblue();
            }
        });

        bdi = findViewById(R.id.bdi);
        bdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openbdi();
            }
        });

        ata = findViewById(R.id.ata);
        ata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openata();
            }
        });

    }

    private void openlifel() {
        Intent lifelintnet = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.lifeline.org.au/"));
        startActivity(lifelintnet);
    }

    private void openbblue() {
        Intent bblueintnet = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.beyondblue.org.au/"));
        startActivity(bblueintnet);
    }

    private void openbdi() {
        Intent bdiintnet = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.blackdoginstitute.org.au/"));
        startActivity(bdiintnet);
    }

    public void openata() {
        Intent ataintent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://anxietyaustralia.com.au"));
        startActivity(ataintent);
    }
}
