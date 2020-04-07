package org.quietlip.guesswhatwho

import android.content.Context
import org.quietlip.guesswhatwho.db.GameDatabase
import org.quietlip.guesswhatwho.models.Game
import org.quietlip.guesswhatwho.network.PixabayApi
import org.quietlip.guesswhatwho.network.RetrofitSingleton

object Repository {
    private var db: GameDatabase? = null
    private lateinit var api: PixabayApi

    fun init(c: Context) {
        api = RetrofitSingleton.getRetrofit().create(PixabayApi::class.java)
        db = GameDatabase.getDb(c)
    }

    suspend fun addGame(game: Game) = db!!.gameDao().insert(game)

    fun getRound(): Int = db!!.gameDao().getRound()

//    fun getHits(searchItem: String): MutableLiveData<Hit>{
//        val response = MutableLiveData<Hit>()
//        api
//
//    }
}