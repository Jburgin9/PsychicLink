package org.quietlip.guesswhatwho.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.quietlip.guesswhatwho.models.Game

@Dao
interface GameDao {
    @Insert
    suspend fun insert(game: Game)
    @Delete
    fun delete(game: Game)
    @Query("SELECT round FROM game")
    fun getRound(): Int
}