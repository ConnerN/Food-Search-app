package com.example.android.final_work;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodNutrientAdapter extends RecyclerView.Adapter<FoodNutrientAdapter.FoodNutrientViewHolder> {
    private static final String TAG = FoodNutrientAdapter.class.getSimpleName();
    private ArrayList<FoodNutrientItem> foodNutrientItems;

    public FoodNutrientAdapter(){
        this.foodNutrientItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public FoodNutrientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.foodnutrient_list_item, parent,false);
        return new FoodNutrientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodNutrientAdapter.FoodNutrientViewHolder holder, int position) {
        holder.bind(this.foodNutrientItems.get(position));
    }

    public void updateFoodNutrientData(ArrayList<FoodNutrientItem> foodNutrientItems){
        this.foodNutrientItems = foodNutrientItems;
    }

    @Override
    public int getItemCount() {
        return this.foodNutrientItems.size();
    }

    class FoodNutrientViewHolder extends RecyclerView.ViewHolder{
        final private TextView foodNutrientNameTV;
        final private TextView foodNutrientUnitTV;
        final private TextView foodNutrientValueTV;

        public FoodNutrientViewHolder(View itemView) {
            super(itemView);
            foodNutrientNameTV = itemView.findViewById(R.id.food_nutrient_name);
            foodNutrientUnitTV = itemView.findViewById(R.id.food_nutrient_unit);
            foodNutrientValueTV = itemView.findViewById(R.id.food_nutrient_value);
        }

        public void bind(FoodNutrientItem foodNutrientItem){
            foodNutrientNameTV.setText(foodNutrientItem.getFoodNutrientName());
            foodNutrientUnitTV.setText(foodNutrientItem.getFoodNutrientUnit());
            foodNutrientValueTV.setText(String.valueOf(foodNutrientItem.getFoodNutrientValue()));
            //Log.d(TAG, "getFoodNutrientValue()"+String.valueOf(foodNutrientItem.getFoodNutrientValue()));
        }
    }
}
