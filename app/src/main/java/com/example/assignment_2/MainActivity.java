package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;



    ImageButton link;
    ImageButton diary;
    ImageButton youtubebtn;
    ImageButton mapsbtn;

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

        mapsbtn = findViewById(R.id.mapsbtn);
        mapsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMapsActivity();
            }
        });


    }


    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
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

    public void openMapsActivity() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


}
