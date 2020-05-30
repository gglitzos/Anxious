package com.example.assignment_2;


import android.content.ContentValues;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.MyViewHolder> {

    private Context context;
    private List<Journal> journalList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView journal;
        public TextView dot;
        public TextView timestamp;

        public MyViewHolder(View view) {
            super(view);
            journal = view.findViewById(R.id.journal);
            dot = view.findViewById(R.id.dot);
            timestamp = view.findViewById(R.id.timestamp);
        }
    }

    public JournalAdapter(Context context, List<Journal> journalList) {
        this.context = context;
         this.journalList = journalList;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.journal_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Journal journal = journalList.get(position);

        holder.journal.setText(journal.getJournal());

        //Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));
        //formatting and displaying timestamp
        holder.timestamp.setText(formatDate(journal.getTimestamp()));

    }

    @Override
    public int getItemCount() {
        return journalList.size();
    }
    /** Formtatting the timestamp to month and date mm dd*/

    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }
        return "";
    }


}
