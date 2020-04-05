package org.quietlip.guesswhatwho;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<HitModel> liveData;
    private MutableLiveData<String> theme;
    private MutableLiveData<String> fragNavUpdates;
    private PixabayRepository repository;

    public void init(){
        if(liveData == null){
            repository = PixabayRepository.getInstance();
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
}
