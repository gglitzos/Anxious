package com.example.assignment_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class JournalMain extends AppCompatActivity {
    private JournalAdapter mAdapter;
    private List<Journal> journalList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noJournalView;

    private DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //coordinatorLayout = findViewById(R.id.coordinator_layout);
        recyclerView = findViewById(R.id.recycler_view);
        noJournalView = findViewById(R.id.empty_journals_view);

        db = new DatabaseHelper(this);

        journalList.addAll(db.getAllJournals());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJournalDialog(false, null, -1);
            }
        });

        mAdapter = new JournalAdapter(this, journalList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyJournals();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);

            }
        }));
    }

    //to insert a new not in the database

    private void createJournal(String journal) {
        //putting the new entry and receiving the id for the entry
        long id = db.insertJournal(journal);

        Journal j = db.getJournal(id);

        if (j != null) {
            //adding the new journal to the array
            journalList.add(0, j);

            mAdapter.notifyDataSetChanged();
            toggleEmptyJournals();
        }
    }

    private void updateJournal(String journal, int position) {
        Journal j = journalList.get(position);
        //updating the text
        j.setJournals(journal);

        ///update in database
        db.updateJournal(j);

        //refresh
        journalList.set(position, j);
        mAdapter.notifyItemChanged(position);

        toggleEmptyJournals();
    }

    private void deleteJournal(int position) {
        //delete entry from db
        db.deleteJournal(journalList.get(position));

        //remove the entry
        journalList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyJournals();

    }

    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showJournalDialog(true, journalList.get(position), position);
                } else {
                    deleteJournal(position);
                }
            }
        });
        builder.show();
    }
    private void showJournalDialog(final boolean shouldUpdate, final Journal journal, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.journal_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(JournalMain.this);
        alertDialogBuilderUserInput.setView(view);

        final EditText inputJournal = view.findViewById(R.id.journal);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_journal_title) : getString(R.string.lbl_edit_journal_title));

        if (shouldUpdate && journal != null) {
            inputJournal.setText(journal.getJournal());
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message when no text is entered
                if (TextUtils.isEmpty(inputJournal.getText().toString())) {
                    Toast.makeText(JournalMain.this, "Enter note!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                // check if user updating note
                if (shouldUpdate && journal != null) {
                    // update note by it's id
                    updateJournal(inputJournal.getText().toString(), position);
                } else {
                    // create new note
                    createJournal(inputJournal.getText().toString());
                }
            }
        });
    }

    private void toggleEmptyJournals() {
        // you can check notesList.size() > 0

        if (db.getJournalsCount() > 0) {
            noJournalView.setVisibility(View.GONE);
        } else {
            noJournalView.setVisibility(View.VISIBLE);
        }
    }
}




