package com.example.android.final_work.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodHistoryDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(FoodString name);

    @Delete
    void delete(FoodString name);

    @Query("SELECT * FROM foodName")
    LiveData<List<FoodString>> getAllRepos();
}
