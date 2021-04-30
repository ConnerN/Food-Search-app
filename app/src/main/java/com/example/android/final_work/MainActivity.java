package com.example.android.final_work;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.android.final_work.data.FoodRepo;
import com.example.android.final_work.data.FoodString;
import com.example.android.final_work.data.LoadingStatus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FoodSearchAdapter.OnSearchResultClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String SEARCH_RESULTS_LIST_KEY = "MainActivity.searchResultsList";

    private RecyclerView searchResultsRV;
    private EditText searchBoxET;
    private ProgressBar loadingIndicatorPB;
    private TextView errorMessageTV;

    private FoodSearchAdapter githubSearchAdapter;
    private FoodSearchViewModel githubSearchViewModel;

    private FoodNameRepoViewModel foodNameRepoViewModel;
    private FoodString foodString;

    private RequestQueue requestQueue;

    private ArrayList<FoodRepo> searchResultsList;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.searchBoxET = findViewById(R.id.et_search_box);
        this.searchResultsRV = findViewById(R.id.rv_search_results);
        this.loadingIndicatorPB = findViewById(R.id.pb_loading_indicator);
        this.errorMessageTV = findViewById(R.id.tv_error_message);

        this.searchResultsRV.setLayoutManager(new LinearLayoutManager(this));
        this.searchResultsRV.setHasFixedSize(true);

        this.githubSearchAdapter = new FoodSearchAdapter(this);
        this.searchResultsRV.setAdapter(this.githubSearchAdapter);

        this.foodNameRepoViewModel = new ViewModelProvider(
                this,
                new ViewModelProvider.AndroidViewModelFactory(getApplication())
        ).get(FoodNameRepoViewModel.class);

        this.foodString = new FoodString();
        this.mediaPlayer = MediaPlayer.create(this,R.raw.m2);


//        this.requestQueue = Volley.newRequestQueue(this);

        this.githubSearchViewModel = new ViewModelProvider(this)
                .get(FoodSearchViewModel.class);

        this.githubSearchViewModel.getSearchResults().observe(
                this,
                new Observer<List<FoodRepo>>() {
                    @Override
                    public void onChanged(List<FoodRepo> gitHubRepos) {
                        githubSearchAdapter.updateSearchResults(gitHubRepos);
                    }
                }
        );

        this.githubSearchViewModel.getLoadingStatus().observe(
                this,
                new Observer<LoadingStatus>() {
                    @Override
                    public void onChanged(LoadingStatus loadingStatus) {
                        if (loadingStatus == LoadingStatus.LOADING) {
                            loadingIndicatorPB.setVisibility(View.VISIBLE);
                        } else if (loadingStatus == LoadingStatus.SUCCESS) {
                            loadingIndicatorPB.setVisibility(View.INVISIBLE);
                            searchResultsRV.setVisibility(View.VISIBLE);
                            errorMessageTV.setVisibility(View.INVISIBLE);
                        } else {
                            loadingIndicatorPB.setVisibility(View.INVISIBLE);
                            searchResultsRV.setVisibility(View.INVISIBLE);
                            errorMessageTV.setVisibility(View.VISIBLE);
                        }
                    }
                }
        );

        Button searchButton = (Button)findViewById(R.id.btn_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = searchBoxET.getText().toString();
                if (!TextUtils.isEmpty(searchQuery)) {
                    githubSearchViewModel.loadSearchResults(searchQuery);
                    foodString.foodName = searchQuery;
                    foodNameRepoViewModel.insertFoodName(foodString);
                    //Log.d(TAG, "searchQuery Here:" + searchQuery);
                    //mediaPlayer.start();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        //Log.d(TAG, "History Here");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_history:
                Intent intent = new Intent(this, HistoryActivity.class);
                Log.d(TAG, "action_history Here:");
                startActivity(intent);
                return true;

            case R.id.action_play:
                mediaPlayer.start();
                Log.d(TAG, "action_play Here:");
                return true;

            case R.id.action_pause:
                mediaPlayer.pause();
                Log.d(TAG, "action_pause Here:" );
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSearchResultClicked(FoodRepo repo) {
        //Log.d(TAG, "Search result clicked: " + repo.fullName);
        Intent intent = new Intent(this, RepoDetailActivity.class);
        intent.putExtra(RepoDetailActivity.EXTRA_GITHUB_REPO, repo);
        startActivity(intent);
    }
}