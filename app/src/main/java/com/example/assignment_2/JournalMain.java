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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class JournalMain extends AppCompatActivity {

    private JournalAdapter mAdapter;
    private List<Journal> journalList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noJournalsView;
    private FloatingActionButton fab;
    private DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.co_layout);
        recyclerView = findViewById(R.id.recycler_view);
        noJournalsView = findViewById(R.id.empty_journal_view);




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

        toggleEmptyJournal();

        //on press open the options to delete, edit and choose

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);

            }
        }));
    }

    //to insert the new journal into the database
    private void createJournal(String journal) {
        long id = db.insertJournal(journal);

        Journal j = db.getJournal(id);

        if (j != null) {
            journalList.add(0, j); //adding the new journal to the database
            mAdapter.notifyDataSetChanged(); //refresh the list
            toggleEmptyJournal();
        }
    }

    //updating the journal
    private void updateJournal(String journal, int position) {
        Journal j = journalList.get(position);
        j.setJournal(journal); //updating the text
        db.updateJournal((j));
        journalList.set(position, j); //updating the list
        mAdapter.notifyItemChanged(position);

        toggleEmptyJournal();
    }

    private void deleteJournal(int position) {
        db.deleteJournal(journalList.get(position));
        //removing the note from the list
        journalList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyJournal();
    }


   private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"EDIT", "DELETE"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Option");
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

    //Shows the alert dialog box to allow you to edit the text and enter the journal entry

    private void showJournalDialog(final boolean shouldUpdate, final Journal journal, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.journal_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(JournalMain.this);
        alertDialogBuilderUserInput.setView(view);

        final EditText inputJournal = view.findViewById(R.id.note);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_journal_title) : getString(R.string.lbl_edit_journal_title));

        if (shouldUpdate && journal != null) {
            inputJournal.setText(journal.getJournal());
        }
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        dialogBox.cancel();
                    }
                });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(inputJournal.getText().toString())) {  //show toast when nothing is entered
                    Toast.makeText(JournalMain.this, "Enter Journal", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                //check if the user is updating the journal
                if(shouldUpdate && journal != null) {
                    updateJournal(inputJournal.getText().toString(), position);
                } else {
                    //create the new journal entry
                    createJournal(inputJournal.getText().toString());
                }
            }
        });
    }

    private void toggleEmptyJournal() {
        if (db.getJournalsCount() > 0) {
            noJournalsView.setVisibility(View.GONE);
        } else {
            noJournalsView.setVisibility(View.VISIBLE);
        }
    }

}











