package org.quietlip.guesswhatwho.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import org.quietlip.guesswhatwho.models.Game

@Dao
interface GameDao {
    @Insert
    fun insert(game: Game)
    @Delete
    fun delete(game: Game)
}