package com.example.assignment_2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class YTAdapter extends RecyclerView.Adapter<YTAdapter.VideoViewHolder> {

    List<YouTubeVideos> youTubeVideosList;

    public YTAdapter() {

    }

    public YTAdapter(List<YouTubeVideos> youTubeVideosList) {
        this.youTubeVideosList = youTubeVideosList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext()).inflate(R.layout.view_video, parent, false);
        return new VideoViewHolder(view);

    }

    @Override
    public void onBindViewHolder (VideoViewHolder holder, int position) {
        holder.videoWeb.loadData( youTubeVideosList.get(position).getVideoUrl(), "text/html" , "utf-8");
    }

    @Override
    public int getItemCount() { return youTubeVideosList.size(); }

    public class VideoViewHolder extends RecyclerView.ViewHolder{
        WebView videoWeb;

        public VideoViewHolder(View itemView) {
            super(itemView);

            videoWeb = (WebView) itemView.findViewById(R.id.WebView);

            videoWeb.getSettings().setJavaScriptEnabled(true);
            videoWeb.setWebChromeClient(new WebChromeClient() {

            });
        }
    }

}
