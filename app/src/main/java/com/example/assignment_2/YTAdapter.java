package com.example.assignment_2;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class YTAdapter extends RecyclerView.Adapter<YTAdapter.VideoViewHolder> {

    List<YouTubeVideos> youTubeVideosList;

    public YTAdapter() {

    }

    public YTAdapter(List<YouTubeVideos> youTubeVideosList) {
        this.youTubeVideosList = youTubeVideosList;
    }

    

}
