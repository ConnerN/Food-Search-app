package com.example.android.final_work.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FoodNameRepoRepository {
    private FoodHistoryDao dao;

    public FoodNameRepoRepository (Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        this.dao = db.foodHistoryDao();
    }

    public void insertFoodNameRepo(FoodString foodName){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(foodName);
            }
        });
    }

    public LiveData<List<FoodString>> getAllGitHubRepos() {
        return this.dao.getAllRepos();
    }
}
