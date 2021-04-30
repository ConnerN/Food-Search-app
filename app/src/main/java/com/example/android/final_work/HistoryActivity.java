package com.example.android.final_work;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.android.final_work.data.FoodRepo;
import com.example.android.final_work.data.FoodString;

import java.util.List;

public class HistoryActivity extends AppCompatActivity implements FoodNameAdapter.OnLocationItemClickListener{

    private FoodNameRepoViewModel viewModel;
    private RecyclerView food_name;
    private FoodNameAdapter adapter;
    private FoodString  foodString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        this.food_name = findViewById(R.id.food_name_list);
        this.food_name.setLayoutManager(new LinearLayoutManager(this));
        this.food_name.setHasFixedSize(true);

        this.adapter = new FoodNameAdapter((FoodNameAdapter.OnLocationItemClickListener) this);
        this.food_name.setAdapter(adapter);

        this.foodString = new FoodString();

        this.viewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(FoodNameRepoViewModel.class);

        this.viewModel.getAllGitHubRepos().observe(
                this,
                new Observer<List<FoodString>>() {
                    @Override
                    public void onChanged(List<FoodString> foodRepos) {
                        adapter.updateLocationItems(foodRepos);
                    }
                }
        );

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                ((FoodNameAdapter.LocationItemViewHolder)viewHolder).removeFromList();
            }
        };

    }

    @Override
    public void onLocationItemClick(FoodString locationHolder) {

    }
}