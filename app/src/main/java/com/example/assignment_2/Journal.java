package com.example.assignment_2;

public class Journal {
    public static final String TABLE_NAME = "journals";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_JOURNAL = "journal";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String journal;
    private String timestamp;

    //Creating the SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE" + TABLE_NAME + "("
            + COLUMN_ID + "INTEGER PRIMARY AUTOINCREMENT,"
            + COLUMN_JOURNAL + " TEXT,"
            + COLUMN_TIMESTAMP + "DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";

    public Journal() {

    }

    public Journal(int id, String journal, String timestamp) {
        this.id = id;
        this.journal = journal;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String string) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }


}
