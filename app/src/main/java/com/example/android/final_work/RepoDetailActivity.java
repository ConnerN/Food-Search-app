package com.example.android.final_work;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.final_work.data.FoodNutrientRepo;
import com.example.android.final_work.data.FoodRepo;
import com.example.android.final_work.data.FoodSearchResults;

import java.util.ArrayList;

public class RepoDetailActivity extends AppCompatActivity {
    public static final String EXTRA_GITHUB_REPO = "FoodRepo";

    private static final String TAG = RepoDetailActivity.class.getSimpleName();

    private Toast errorToast;

    private FoodRepo repo;

    private ArrayList<FoodNutrientItem> foodNutrientItems;
    private RecyclerView foodNutrientRV;
    private FoodNutrientAdapter foodNutrientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_GITHUB_REPO)) {
            this.repo = (FoodRepo)intent.getSerializableExtra(EXTRA_GITHUB_REPO);
            //Log.d(TAG, "Got repo with name: " + repo.fullName);
            //Log.d(TAG, "foodNutrientsList: " + repo);

            TextView repoNameTV = findViewById(R.id.tv_repo_name);
            TextView repoStarsTV = findViewById(R.id.tv_repo_score);
            TextView repoPubDateTV = findViewById(R.id.tv_repo_pubDate);
            TextView userPromptTV = findViewById(R.id.tv_user_prompt);



            repoNameTV.setText(repo.fullName);
            repoStarsTV.setText(String.valueOf("Score: " + repo.score));
            repoPubDateTV.setText("Publish Date: "+ repo.pubDate);
            userPromptTV.setText("Check Nutrients Below:");


            this.foodNutrientRV = findViewById(R.id.rv_foodNutrient_list);
            this.foodNutrientRV.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
            //this.foodNutrientRV.setHasFixedSize(true);

            initFoodNutrientItem();
            this.foodNutrientAdapter = new FoodNutrientAdapter();
            this.foodNutrientRV.setAdapter(this.foodNutrientAdapter);

            int num = repo.foodNutrients.size();
            for(int i = 0; i < num; i++) {
                foodNutrientItems.add(new FoodNutrientItem(
                        repo.foodNutrients.get(i).nutrientName,
                        repo.foodNutrients.get(i).unitName,
                        repo.foodNutrients.get(i).value
                ));
                foodNutrientAdapter.updateFoodNutrientData(foodNutrientItems);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.repo_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view_on_web:
                viewRepoOnWeb();
                return true;
            case R.id.action_share:
                shareRepo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void viewRepoOnWeb() {
        if (this.repo != null) {
            Uri foodRepoUri = Uri.parse("https://www.google.com/search?q="+repo.fullName);
            Intent intent = new Intent(Intent.ACTION_VIEW, foodRepoUri);
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                if (this.errorToast != null) {
                    this.errorToast.cancel();
                }
                this.errorToast = Toast.makeText(this, "Error...", Toast.LENGTH_LONG);
                this.errorToast.show();
            }
        }
    }

    private void shareRepo() {
        if (this.repo != null) {
            String shareText = getString(
                    R.string.share_repo_text,
                    this.repo.fullName,
                    this.repo.pubDate
            );
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, shareText);
            intent.setType("text/plain");

            Intent chooserIntent = Intent.createChooser(intent, null);
            startActivity(chooserIntent);
        }
    }
    private void initFoodNutrientItem() {this.foodNutrientItems = new ArrayList<>();}
}