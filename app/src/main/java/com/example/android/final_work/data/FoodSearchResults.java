package com.example.android.final_work.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FoodSearchResults {
    @SerializedName("foods")
    public ArrayList<FoodRepo> foodsList;
}
