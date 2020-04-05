package org.quietlip.guesswhatwho;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class PixabayRepository {
    private static PixabayRepository repository;
    private PixabayApi pixabayApi;
    Retrofit instance = RetrofitSingleton.getRetrofit();

    public PixabayRepository(){
        pixabayApi = instance.create(PixabayApi.class);
    }

    public MutableLiveData<HitModel> getHits(String search){
        MutableLiveData<HitModel> responseData = new MutableLiveData<>();
        pixabayApi.getHits().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                Log.d("Main", "onResponse: " + response.body().hits);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
        return responseData;
    }

    public static PixabayRepository getInstance(){
        if (repository == null) repository = new PixabayRepository();
        return repository;
    }

}
