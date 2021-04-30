package com.example.android.final_work.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodService {
    @GET("search?")
    Call<FoodSearchResults> searchRepos(@Query("query") String query, @Query("api_key") String key);

}
