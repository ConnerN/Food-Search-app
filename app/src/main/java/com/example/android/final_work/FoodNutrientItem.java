package com.example.android.final_work;

public class FoodNutrientItem {
    private String foodNutrientName;
    private String foodNutrientUnit;
    private double foodNutrientValue;

    public FoodNutrientItem(String foodNutrientName, String foodNutrientUnit, double foodNutrientValue){
        this.foodNutrientName = foodNutrientName;
        this.foodNutrientUnit = foodNutrientUnit;
        this.foodNutrientValue = foodNutrientValue;
    }

    public String getFoodNutrientName() {
        return foodNutrientName;
    }

    public String getFoodNutrientUnit() {
        return foodNutrientUnit;
    }

    public double getFoodNutrientValue() {
        return foodNutrientValue;
    }
}
