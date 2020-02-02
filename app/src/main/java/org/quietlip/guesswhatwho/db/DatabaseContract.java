package org.quietlip.guesswhatwho.db;

import android.provider.BaseColumns;

public class DatabaseContract {
    private DatabaseContract(){}

    public static class GameEntry implements BaseColumns {
        public static final String TABLE_NAME = "mystery_Game";
        public static final String COLUMN_NAME_SCORE = "score";
        public static final String COLUMN_NAME_GUESS = "guess";
        public static final String COLUMN_NAME_GUESS_CORRECT = "guess_correct";
    }

    static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            GameEntry.TABLE_NAME + " (" + GameEntry._ID + " INTEGER PRIMARY KEY," +
            GameEntry.COLUMN_NAME_GUESS + " INTEGER," +
            GameEntry.COLUMN_NAME_GUESS_CORRECT + " INTEGER, " +
            // 0 (FALSE) && 1 (TRUE)
            GameEntry.COLUMN_NAME_SCORE + "INTEGER)";

    static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + GameEntry.TABLE_NAME;

    //col: key(round), guess, correct||incorrect, w/l ration

}
