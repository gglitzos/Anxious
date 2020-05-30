package com.example.assignment_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database Version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "journals_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create the Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Journal Table
        db.execSQL(Journal.CREATE_TABLE);
    }

    //Upgrading Database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if it exists
        db.execSQL(" DROP TABLE IF EXISTS " + Journal.TABLE_NAME);

        //Create table again
        onCreate(db);
    }


    /*Method to insert journal entries into the database */

    public long insertJournal(String journal) {
        // get the database writable to insert data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //the "id" and the "timestamp" will be inserted automatically
        values.put(Journal.COLUMN_JOURNAL, journal);

        // insert row
        long id = db.insert(Journal.TABLE_NAME, null, values);

        //close the database connection
        db.close();

        //return the newly inserted row
        return id;
    }

    /* Getting read access in the database */
     public Journal getJournal(long id) {
         //get readable database as we are not inserting anything
         SQLiteDatabase db = this.getReadableDatabase();

         Cursor cursor = db.query(Journal.TABLE_NAME,
                 new String[]{Journal.COLUMN_ID, Journal.COLUMN_JOURNAL, Journal.COLUMN_TIMESTAMP},
                 Journal.COLUMN_ID + "=?",
                 new String[]{String.valueOf(id)}, null, null, null, null);
         if (cursor != null)
             cursor.moveToFirst();

         //prepare journal
         Journal journal = new Journal(
                 cursor.getInt(cursor.getColumnIndex(Journal.COLUMN_ID)),
                 cursor.getString(cursor.getColumnIndex(Journal.COLUMN_JOURNAL)),
                 cursor.getString(cursor.getColumnIndex(Journal.COLUMN_TIMESTAMP)));

         //close the db connection
         cursor.close();

         return journal;

     }

      public List<Journal> getAllJournals() {
         List<Journal> journals = new ArrayList<>();
         // select all
          String selectQuery = "SELECT * FROM " + Journal.TABLE_NAME + " ORDER BY " +
                  Journal.COLUMN_TIMESTAMP + " DESC ";

          SQLiteDatabase db = this.getWritableDatabase();
          Cursor cursor = db.rawQuery(selectQuery, null);

          //looping through all rows and adding to the list
          if (cursor.moveToFirst()) {
              do {
                  Journal journal = new Journal();
                  journal.setId(cursor.getInt(cursor.getColumnIndex(Journal.COLUMN_ID)));
                  journal.setJournal(cursor.getString(cursor.getColumnIndex(Journal.COLUMN_JOURNAL)));
                  journal.setTimestamp(cursor.getString(cursor.getColumnIndex(Journal.COLUMN_TIMESTAMP)));


                  journals.add(journal);
              } while (cursor.moveToNext());
          }

          db.close();

          return journals;
      }

      public int getJournalsCount() {
         String countQuery = "SELECT * FROM " + Journal.TABLE_NAME;
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery(countQuery, null);

         int count = cursor.getCount();
         cursor.close();

         return count;
      }

      public int getJournalCount() {
         String countQuery = "SELECT * FROM " + Journal.TABLE_NAME;
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor cursor = db.rawQuery(countQuery, null);

         int count = cursor.getCount();
         cursor.close();

         // return count
          return count;
      }

      public int updateJournal(Journal journal) {
         SQLiteDatabase db = this.getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put(Journal.COLUMN_JOURNAL, journal.getJournal());

         // update row
          return db.update(Journal.TABLE_NAME, values, Journal.COLUMN_ID + " =?",
                  new String[] {String.valueOf((journal.getId()))});
      }

      public void deleteJournal (Journal journal) {
         SQLiteDatabase db = this.getWritableDatabase();
         db.delete(Journal.TABLE_NAME, Journal.COLUMN_ID + " = ?",
                 new String[]{String.valueOf(journal.getId())});
         db.close();
      }

}
