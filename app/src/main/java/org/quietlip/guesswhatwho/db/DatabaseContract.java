package org.quietlip.guesswhatwho.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    private DatabaseContract(){}

    public static class GameEntry implements BaseColumns {
        public static final String TABLE_NAME = "mystery_Game";
        public static final String COLUMN_NAME_ROUND = "round";
        public static final String COLUMN_NAME_SCORE = "score";
        public static final String COLUMN_NAME_GUESS = "guess";
    }

    static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            GameEntry.TABLE_NAME + " (" + GameEntry._ID + " INTEGER PRIMARY KEY," +
            GameEntry.COLUMN_NAME_ROUND + " TEXT," +
            GameEntry.COLUMN_NAME_SCORE + " INTEGER," +
            GameEntry.COLUMN_NAME_GUESS + " INTEGER)";

    static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + GameEntry.TABLE_NAME;
}
