package com.example.android.final_work.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "foodName")
public class FoodString {
    @PrimaryKey
    @NonNull
    public String foodName;
}
