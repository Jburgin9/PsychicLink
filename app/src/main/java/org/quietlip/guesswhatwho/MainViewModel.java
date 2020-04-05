package org.quietlip.guesswhatwho;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<HitModel> liveData;
    private PixabayRepository repository;

    public void init(){
        if(liveData == null){
            repository = PixabayRepository.getInstance();
            liveData = repository.getHits("13327476-8902cdfd39d999b93b55dcde7");
        }
    }

    public LiveData<HitModel> getLiveData(){
        return liveData;
    }
}
