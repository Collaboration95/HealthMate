/**

 This class represents the Trends page in the HealthMate app.
 It displays a list of trends for various activities and allows the user
 to navigate to other pages in the app using a bottom navigation view.
 */
package com.example.healthmate;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

/**
The Trend class represents the Trends page in the HealthMate app. It has an instance variable bottomNavigationView to manage the bottom navigation view and a
Context variable to store the current context. The class also has an ArrayList to store the data for the list of trends.
 **/
public class Trend extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Intent intent;
    Context context = Trend.this;
    ArrayList<Trend_Class> data = new ArrayList<>();

    /**
     * Initializes the data for the list of trends and sets up the RecyclerView and adapter.
     */
    private void setUpData() {
        String[] activityName = {"Move", "Exercise", "Move", "Distance", "RunningPace", "Walking Pace"};
        String[] activityNumber = {"1268", "1284", "1284", "1284", "1284", "1284"};
        String[] activityUnit = {"Kcal/day", "Kcal/day", "Kcal/day", "Kcal/day", "Kcal/day", "Kcal/day"};
        for (int i = 0; i < activityUnit.length; i++) {
            data.add(new Trend_Class(activityName[i], activityNumber[i], activityUnit[i], R.drawable.ic_launcher_add));
        }
    }

    /**
     * Sets up the layout and functionality for the Trends page.
     * It sets up the RecyclerView and adapter by calling setUpData method. It also sets up the bottom navigation view and handles navigation to other pages in the app.
     * Finally, it sets up the card view and popup menu, which allow the user to perform additional actions on the Trends page.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend);

        // Set up RecyclerView and adapter
        RecyclerView recycle = findViewById(R.id.trend_recyler);
        setUpData();
        Trend_Adapter adapter = new Trend_Adapter(this, data);
        recycle.setAdapter(adapter);
        recycle.setLayoutManager(new LinearLayoutManager(this));

        // Set up bottom navigation view
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.trends);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.calorie:
                        Toast.makeText(context, "Calorie", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, MainActivity.class));
                        return true;
                    case R.id.run:
                        Toast.makeText(context, "Run", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, Run.class));
                        return true;
                    case R.id.social:
                        Toast.makeText(context, "Social", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, Social.class));
                        return true;
                    case R.id.trends:
                        Toast.makeText(context, "Trends", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

        // Set up card view and popup menu
        CardView cardView = findViewById(R.id.bringtoFront);
        cardView.bringToFront();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(context, "Selected: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        if (item.getItemId() == R.id.newMeal) {
                            startActivity(new Intent(context, NewMeal.class));
                        } else if (item.getItemId() == R.id.newRun) {
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
    }
}


