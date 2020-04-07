package org.quietlip.guesswhatwho.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.quietlip.guesswhatwho.models.Game

@Database(entities = [Game::class], version = 1)
abstract class GameDatabase : RoomDatabase(){
    abstract fun gameDao(): GameDao
    companion object {
        private var instance: GameDatabase? = null
        fun getDb(context: Context): GameDatabase?{
            if(instance == null){
                synchronized(GameDatabase::class){
                    instance = Room.databaseBuilder(context.applicationContext, GameDatabase::class.java, "game.db").build()
                }
            }
            return instance
        }
    }
}