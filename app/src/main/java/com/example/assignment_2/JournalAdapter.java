package com.example.assignment_2;

import android.content.ContentValues;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.text.UFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.MyViewHolder> {

    private Context context;
    private List<Journal> journalList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView journal;
        public TextView timestamp;

        public MyViewHolder(View view) {
            super(view);
            journal = view.findViewById(R.id.journal);
            timestamp = view.findViewById(R.id.timestamp);
        }
    }

    public JournalAdapter(Context context, List<Journal>journalList) {
        this.context = context;
        this.journalList = journalList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.journal_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Journal journal = journalList.get(position);

        holder.journal.setText(journal.getJournal());

        //formatting timestamp
        holder.timestamp.setText(formatDate(journal.getTimestamp()));

    }

    @Override
    public int getItemCount() {
        return journalList.size();
    }

    //timestamp will be date/month format
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String formatDate(String dareStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
            Date date = fmt.parse(dareStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("dd MM");
            return fmtOut.format(date);

        } catch (ParseException e) {

        }
        return "";
    }
}
