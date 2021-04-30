package com.example.android.final_work.data;

import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodSearchRepository {
    private static final String TAG = FoodSearchRepository.class.getSimpleName();
    private static final String BASE_URL = "https://api.nal.usda.gov/fdc/v1/foods/";

    private MutableLiveData<List<FoodRepo>> searchResults;
    private MutableLiveData<LoadingStatus> loadingStatus;

    private String currentQuery;

    private FoodService gitHubService;

    public FoodSearchRepository() {
        this.searchResults = new MutableLiveData<>();
        this.searchResults.setValue(null);

        this.loadingStatus = new MutableLiveData<>();
        this.loadingStatus.setValue(LoadingStatus.SUCCESS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.gitHubService = retrofit.create(FoodService.class);
    }

    public LiveData<List<FoodRepo>> getSearchResults() {
        return this.searchResults;
    }

    public LiveData<LoadingStatus> getLoadingStatus() {
        return this.loadingStatus;
    }

    public void loadSearchResults(String query) {
        if (shouldExecuteSearch(query)) {
            this.currentQuery = query;
            this.searchResults.setValue(null);
            this.loadingStatus.setValue(LoadingStatus.LOADING);
            //Log.d(TAG, "running new search for this query: " + query);
            Call<FoodSearchResults> results = this.gitHubService.searchRepos(query,"uX4FFqfEXWl5c4pkbHFPdY21jKBviWwy0P6k4H1R");
            results.enqueue(new Callback<FoodSearchResults>() {
                @Override
                public void onResponse(Call<FoodSearchResults> call, Response<FoodSearchResults> response) {
                    if (response.code() == 200) {
                        searchResults.setValue(response.body().foodsList);
                        //Log.d(TAG, "response.body().foodsList: " + response.body().foodsList.get(0).getFoodNutrients().get(0).nutrientName);
                        loadingStatus.setValue(LoadingStatus.SUCCESS);
                    } else {
                        loadingStatus.setValue(LoadingStatus.ERROR);
                    }
                }

                @Override
                public void onFailure(Call<FoodSearchResults> call, Throwable t) {
                    t.printStackTrace();
                    loadingStatus.setValue(LoadingStatus.ERROR);
                    //Log.d(TAG, "ERROR HERE");
                }
            });
        } else {
            Log.d(TAG, "using cached results for this query: " + query);
        }
    }

    private boolean shouldExecuteSearch(String query) {
        return !TextUtils.equals(query, this.currentQuery)
                || this.loadingStatus.getValue() == LoadingStatus.ERROR;
    }
}
