package org.quietlip.guesswhatwho;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PixabayApi {
//    @GET("api/")
//    public Call<Response> getHits(@Query("key") String key,
//                                  @Query("q") String search,
//                                  @Query("image_type") String type);

    @GET("?key=13327476-8902cdfd39d999b93b55dcde7&q=yellow+flowers&image_type=photo")
    public Call<Response> getHits();
}

