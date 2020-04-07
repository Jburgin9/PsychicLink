package org.quietlip.guesswhatwho

import android.content.Context
import androidx.lifecycle.MutableLiveData
import org.quietlip.guesswhatwho.db.AppDatabase
import org.quietlip.guesswhatwho.db.GameDao
import org.quietlip.guesswhatwho.models.Game
import org.quietlip.guesswhatwho.models.Hit
import org.quietlip.guesswhatwho.models.Response
import org.quietlip.guesswhatwho.network.PixabayApi
import org.quietlip.guesswhatwho.network.RetrofitSingleton
import retrofit2.Callback

object Repository {
    private lateinit var dao: GameDao
    private lateinit var api: PixabayApi

    fun init(c: Context) {
        api = RetrofitSingleton.getRetrofit().create(PixabayApi::class.java)
        dao = AppDatabase.getInstance(c.applicationContext).gameDAO()
    }

    fun addGame(game: Game) = dao.insert(game)

//    fun getHits(searchItem: String): MutableLiveData<Hit>{
//        val response = MutableLiveData<Hit>()
//        api
//
//    }
}