package org.quietlip.guesswhatwho;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

public class MainViewModel extends ViewModel {
    private MutableLiveData<HitModel> liveData;
    private MutableLiveData<String> theme;
    private MutableLiveData<String> fragNavUpdates;
    private GameRepository repository;

    public void init(Context context){
        if(liveData == null){
            repository = GameRepository.getInstance(context.getApplicationContext());
            Room.databaseBuilder(context, AppDatabase.class, "game.db").build();
            liveData = repository.getHits("13327476-8902cdfd39d999b93b55dcde7");
            theme = new MutableLiveData<>();
            fragNavUpdates = new MutableLiveData<>();
        }
    }

    public LiveData<HitModel> getLiveData(){
        return liveData;
    }

    public void selectedItem(String str){
        theme.setValue(str);
    }

    public MutableLiveData<String> getTheme(){
        return theme;
    }

    public void setFragDestination(String fragNumber){
        fragNavUpdates.setValue(fragNumber);
    }

    public MutableLiveData<String> getFragNavUpdates(){
        return fragNavUpdates;
    }

    public void insertGame(Game game){
        repository.addGame(game);
    }

}
