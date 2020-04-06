package org.quietlip.guesswhatwho

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface GameDao {
    @Insert
    fun insert(game: Game)
    @Delete
    fun delete(game: Game)
}