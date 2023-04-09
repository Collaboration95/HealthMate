package com.example.healthmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyObserver{
    private Button button;
    //    Variable to store context
    Context context = MainActivity.this;
    BottomNavigationView bottomNavigationView;
    CircularProgressIndicator circularProgressIndicator;
    MealAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<plus_AddMeal> data = newMealHolder.getInstance().getData();
        RecyclerView recyclerView = findViewById(R.id.Mealrecyclerview);
        adapter = new MealAdapter(this,data);

        if (data.size()>0) {
            recyclerView.setAdapter(new MealAdapter(this, data));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        newMealHolder.getInstance().addObserver(this);


        updateUI(); // Call updateUI() method here

        CardView cardView = findViewById(R.id.bringtoFront);
        cardView.bringToFront();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this, "Selected: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        if (item.getItemId()==R.id.newMeal){
                            startActivity(new Intent(context,NewMeal.class));
                        } else if (item.getItemId()==R.id.newRun) {
                            startActivity(new Intent(context, MapsActivity.class));
                        }
                        else if (item.getItemId()==R.id.newPost){
                            startActivity(new Intent(context, NewPost.class));
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
//       Intent
        CardView cardView2 = findViewById(R.id.plusCard); // Replace "my_button_id" with your actual button ID
        cardView2.bringToFront();
//       Following Code block to be included in every activity to take care of bottom navigation view functionality
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean   onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.calorie:
                        Toast.makeText(context, "Calorie", Toast.LENGTH_SHORT).show();
                        Log.d("Great Success", newMealHolder.getInstance().getMealCount() + "");
                        return true;

                    case R.id.run:
                        Toast.makeText(context, "Run", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, run.class));
                        return true;

                    case R.id.social:
                        Toast.makeText(context, "Social", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, social.class));
                        return true;

                    case R.id.trends:
                        Toast.makeText(context, "Trends", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, trend.class));
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void OnChange() {
        updateUI(); // Call updateUI() method here
    }

    public void updateUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Find and initialize UI elements
                CircularProgressIndicator circularProgressIndicator = findViewById(R.id.circularProgressIndicator);
                TextView totalCalorie = findViewById(R.id.textCalorie);
                ProgressBar progressBarProtein = findViewById(R.id.proteinProgress);
                ProgressBar progressBarFat = findViewById(R.id.fatProgress);
                ProgressBar progressBarCarbs = findViewById(R.id.CarbProgress);
                int totalprotein = newMealHolder.getInstance().TotalProtein()*4;
                int totalFat = newMealHolder.getInstance().TotalFat()*9;
                int totalCarbs = newMealHolder.getInstance().TotalCarbs()*4;
                int totalFood = totalCarbs + totalFat + totalprotein;
                // Update progress bars with data from newMealHolder
                if(totalFood!=0) {
                    if (progressBarProtein != null && progressBarFat != null && progressBarCarbs != null) {
                        progressBarFat.setProgress((totalFat * 100) / totalFood);
                        progressBarCarbs.setProgress((totalCarbs * 100) / totalFood);
                        progressBarProtein.setProgress((totalprotein * 100) / totalFood);
                    }
                }

                if(circularProgressIndicator!=null){
                    circularProgressIndicator.setProgress(newMealHolder.getInstance().TotalCalories(),true);
                }


                // Update total calorie text with data from newMealHolder
                if (totalCalorie != null) {
                    totalCalorie.setText(newMealHolder.getInstance().TotalCalories() + " ");
                }
                // Update circular progress indicator with a default value (you might want to update this based on the data)
            }
        });
    }



}