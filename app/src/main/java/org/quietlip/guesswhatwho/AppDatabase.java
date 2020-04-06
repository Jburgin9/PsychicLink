package org.quietlip.guesswhatwho;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Game.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GameDao gameDAO();
    private static volatile AppDatabase instance;

    static AppDatabase getInstance(Context context){
        if(instance == null){
            synchronized (AppDatabase.class){
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "game_database")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return instance;
    }
}
