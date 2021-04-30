package com.example.android.final_work;

import com.example.android.final_work.data.FoodRepo;
import com.example.android.final_work.data.FoodSearchRepository;
import com.example.android.final_work.data.LoadingStatus;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class FoodSearchViewModel extends ViewModel {
    private FoodSearchRepository repository;
    private LiveData<List<FoodRepo>> searchResults;
    private LiveData<LoadingStatus> loadingStatus;

    public FoodSearchViewModel() {
        this.repository = new FoodSearchRepository();
        this.searchResults = this.repository.getSearchResults();
        this.loadingStatus = this.repository.getLoadingStatus();
    }

    public LiveData<List<FoodRepo>> getSearchResults() {
        return this.searchResults;
    }

    public LiveData<LoadingStatus> getLoadingStatus() {
        return this.loadingStatus;
    }

    public void loadSearchResults(String query) {
        this.repository.loadSearchResults(query);
    }
}
