package com.example.android.final_work;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.final_work.data.FoodString;

import java.util.ArrayList;
import java.util.List;

public class FoodNameAdapter extends RecyclerView.Adapter<FoodNameAdapter.LocationItemViewHolder> {

    private List<FoodString> foodStrings;
    private OnLocationItemClickListener mLocationItemClickListener;

    interface OnLocationItemClickListener {
        void onLocationItemClick(FoodString locationHolder);
    }

    public FoodNameAdapter(OnLocationItemClickListener clickListener) {
        foodStrings = new ArrayList<>();
        mLocationItemClickListener = clickListener;
    }

    public void updateLocationItems(List<FoodString> locationHolders) {
        foodStrings = locationHolders;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (foodStrings != null) {
            return foodStrings.size();
        } else {
            return 0;
        }
    }

    @Override
    public LocationItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.food_history_item, parent, false); //change forecast_list_item
        return new LocationItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationItemViewHolder holder, int position) {
        holder.bind(foodStrings.get(position));
    }

    class LocationItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mfood_name;

        public LocationItemViewHolder(View itemView) {
            super(itemView);
            mfood_name = itemView.findViewById(R.id.food_history_result);
            itemView.setOnClickListener(this);
        }

        public void bind(FoodString foodHolder) {
            mfood_name.setText(foodHolder.foodName);
        }

        @Override
        public void onClick(View v) {
            FoodString locationHolder = foodStrings.get(getAdapterPosition());
            mLocationItemClickListener.onLocationItemClick(locationHolder);
        }
        public void removeFromList(){
            int position = getAdapterPosition();
            foodStrings.remove(foodStrings.size()-position-1);
        }
    }
}

