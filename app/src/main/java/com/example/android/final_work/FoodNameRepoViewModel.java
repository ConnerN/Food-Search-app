package com.example.android.final_work;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.final_work.data.FoodNameRepoRepository;
import com.example.android.final_work.data.FoodString;

import java.util.List;

public class FoodNameRepoViewModel extends AndroidViewModel {
    private FoodNameRepoRepository repoRepository;

    public FoodNameRepoViewModel(Application application){
        super(application);
        this.repoRepository = new FoodNameRepoRepository(application);
    }

    public void insertFoodName(FoodString foodName){
        this.repoRepository.insertFoodNameRepo(foodName);
    }

    public LiveData<List<FoodString>> getAllGitHubRepos() {
        return repoRepository.getAllGitHubRepos();
    }
}
