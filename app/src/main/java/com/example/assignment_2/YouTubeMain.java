package com.example.assignment_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Vector;

public class YouTubeMain extends AppCompatActivity {

    RecyclerView recyclerView;
    Vector<YouTubeVideos> youTubeVideos = new Vector<YouTubeVideos>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        //linked videos to youtube
        youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/EpVFSti0Ydg\" frameborder=\"0\" allowfullscreen></iframe>") );
        youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/x0nZ1ZLephQ\" frameborder=\"0\" allowfullscreen></iframe>") );
        youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/q3ZjCW8UT5g\" frameborder=\"0\" allowfullscreen></iframe>") );
        youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ZebSXPUCPFc\" frameborder=\"0\" allowfullscreen></iframe>") );
        youTubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/HqSoxMOrVeE\" frameborder=\"0\" allowfullscreen></iframe>") );

        YTAdapter ytAdapter = new YTAdapter(youTubeVideos);

        recyclerView.setAdapter(ytAdapter);
    }
}
