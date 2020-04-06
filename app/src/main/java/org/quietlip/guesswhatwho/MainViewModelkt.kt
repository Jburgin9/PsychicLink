package org.quietlip.guesswhatwho

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room

class MainViewModelkt : ViewModel() {
    val liveData = MutableLiveData<HitModel>()
    val themeSelector = MutableLiveData<String>()
    val fragSelector = MutableLiveData<String>()
    private lateinit var repo: GameRepository

    fun init(c: Context){
        if(!liveData.hasObservers()){
            repo = GameRepository.getInstance(c.applicationContext)
            Room.databaseBuilder(c, AppDatabase::class.java, "game.db").build()
        }
    }
    fun selectedItem(str: String) {
        themeSelector.value = str
    }

    fun setFragDest(fragNum: String) {
        fragSelector.value = fragNum
    }

    fun insertGame(game: Game) {
        repo.addGame(game)
    }

//    fun passingContextBAD(context: Context) {
//        this.context = context
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        context = null
//    }
}