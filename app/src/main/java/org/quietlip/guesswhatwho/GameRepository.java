package org.quietlip.guesswhatwho;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;

public class GameRepository {
    private static GameRepository repository;
    private PixabayApi pixabayApi;
    private GameDao gameDAO;

    private GameRepository(Context context){
        pixabayApi = RetrofitSingleton.getRetrofit().create(PixabayApi.class);
        AppDatabase database = AppDatabase.getInstance(context.getApplicationContext());
        gameDAO = database.gameDAO();
    }

    static GameRepository getInstance(Context context){
        if (repository == null) repository = new GameRepository(context);
        return repository;
    }

    //Pixabay
    MutableLiveData<HitModel> getHits(String search){
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

    //GameDatabase
    void addGame(Game game){
        gameDAO.insert(game);
    }
}
