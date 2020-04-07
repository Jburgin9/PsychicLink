package org.quietlip.guesswhatwho

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.launch
import org.quietlip.guesswhatwho.db.GameDatabase
import org.quietlip.guesswhatwho.models.Game
import org.quietlip.guesswhatwho.models.Hit

class GameViewModel : ViewModel() {
    val liveData = MutableLiveData<Hit>()
    val themeSelector = MutableLiveData<String>()
    val fragSelector = MutableLiveData<String>()
    private val repo = Repository

    fun init(c: Context){
        if(!liveData.hasObservers()){
            Repository.init(c.applicationContext)
            Room.databaseBuilder(c, GameDatabase::class.java, "game.db").build()
        }

    }
    fun selectedItem(str: String) {
        themeSelector.value = str
    }

    fun setFragDest(fragNum: String) {
        fragSelector.value = fragNum
    }

    fun insertGame(game: Game) = viewModelScope.launch {
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