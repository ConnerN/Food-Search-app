package com.example.android.final_work.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class FoodRepo implements Serializable {
    @SerializedName("description")
    public String fullName;

    @SerializedName("publishedDate")
    public String pubDate;

    public double score;

    public ArrayList<FoodNutrientRepo> foodNutrients;

    public ArrayList<FoodNutrientRepo> getFoodNutrients() {
        return foodNutrients;
    }
}
