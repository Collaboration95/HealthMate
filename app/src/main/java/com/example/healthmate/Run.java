package com.example.healthmate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.Field;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/**
 * The Run activity displays and manages fitness data from Google Fit.
 * It is responsible for fetching and displaying the step count, distance, calories burned,
 * and heart rate, as well as handling bottom navigation and popup menu interactions.
 */
public class Run extends AppCompatActivity {
    // Declare variables for UI elements and GoogleFitManager
    BottomNavigationView bottomNavigationView;
    Context context = Run.this;
    private GoogleFitManager googleFitManager;
    // Declare variables for UI elements
    private TextView stepCountTextView;
    private TextView distanceTextView;
    private TextView caloriesTextView;
    private Button refreshDataButton;
    private Button signOutButton;
    private Button viewFitnessHistoryButton;
    private Button clearHistoryButton;

    /**
     * Initializes the activity, sets up the Google Fit manager, UI elements, and event listeners.
     *
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        // Initialize GoogleFitManager
        googleFitManager = new GoogleFitManager(this, context);

        // Check if the user is signed in and request sign-in if not
        if (!googleFitManager.getSignInStatus()) {
            googleFitManager.requestGoogleFitPermissions();
        }

        // Initialize UI elements
        stepCountTextView = findViewById(R.id.textStepCount);
        distanceTextView = findViewById(R.id.textDistance);
        caloriesTextView = findViewById(R.id.textCaloriesBurnt);
        refreshDataButton = findViewById(R.id.refresh_data_button);
        signOutButton = findViewById(R.id.signOutButton);
        clearHistoryButton = findViewById(R.id.clear_history_button);

        // Add a click listener for the refresh data button
        refreshDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchDataAndUpdateUI();
            }
        });

        // Add a click listener for the sign out button
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleFitManager.signOut(new GoogleFitManager.GoogleSignInResultCallBack() {
                    @Override
                    public void onSignOutSuccess() {
                        Toast.makeText(context, "Signed out successfully.", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onSignOutFailure(Exception e) {
                        Toast.makeText(context, "Sign out failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Add a click listener for the viewFitnessHistory button
        viewFitnessHistoryButton = findViewById(R.id.view_fitness_history_button);
        viewFitnessHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Run.this, FitnessHistory.class);
                startActivity(intent);
            }
        });

        // Add a click listener to clear previous recorded histories
        clearHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FitnessDatabaseHelper dbHelper = new FitnessDatabaseHelper(Run.this);
                dbHelper.clearFitnessHistory();
                // Refresh your list or UI after clearing the history
                Toast.makeText(context, "History successfully cleared!", Toast.LENGTH_SHORT).show();
            }
        });

        // Set up bottom navigation and its event listeners
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.alltrends);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle bottom navigation item clicks
                switch (item.getItemId()) {
                    case R.id.calorie:
                        startActivity(new Intent(context, MainActivity.class));
                        return true;
                    case R.id.alltrends:
                        fetchDataAndUpdateUI(); // Fetch fitness data and update UI elements
                        return true;

                    case R.id.social:
                        startActivity(new Intent(context, Social.class));
                        return true;

                    case R.id.goals:
                        startActivity(new Intent(context, Trend.class));
                        return true;
                }
                return false;
            }
        });

        // Set up the floating action button and its event listener
        CardView cardView = findViewById(R.id.bringtoFront);
        cardView.bringToFront();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create and display a PopupMenu when the floating action button is clicked
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
//                        Toast.makeText(context, "Selected:" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        if (item.getItemId() == R.id.newMeal) {
                            startActivity(new Intent(context, NewMeal.class));
                        } else if (item.getItemId() == R.id.newRun) {
                            startActivity(new Intent(context, MapsActivity.class));
                        } else if (item.getItemId() == R.id.newPost) {
                            startActivity(new Intent(context, NewPost.class));
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    /**
     * Fetches fitness data from Google Fit manager and updates the UI elements.
     */
    private void fetchDataAndUpdateUI() {
        // Get today's step count
        googleFitManager.getTodayStepCount(new GoogleFitManager.OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                final int stepCount = dataPoint.getValue(Field.FIELD_STEPS).asInt();
                stepCountTextView.setText(String.valueOf(stepCount));

                // Get today's distance
                googleFitManager.getTodayDistance(new GoogleFitManager.OnDataPointListener() {
                    @Override
                    public void onDataPoint(DataPoint dataPoint) {
                        float distanceMeters = dataPoint.getValue(Field.FIELD_DISTANCE).asFloat();
                        final float distanceKm = distanceMeters / 1000;
                        String formattedDistance = String.format("%.2f km", distanceKm);
                        distanceTextView.setText(formattedDistance);

                        // Get today's calories burned
                        googleFitManager.getTodayCaloriesBurned(new GoogleFitManager.OnDataPointListener() {
                            @Override
                            public void onDataPoint(DataPoint dataPoint) {
                                float calories = dataPoint.getValue(Field.FIELD_CALORIES).asFloat();
                                String formattedCalories = String.format("%.2f kcal", calories);
                                caloriesTextView.setText(formattedCalories);

                                // Save the fetched fitness data to the local database
                                FitnessData data = new FitnessData(stepCount, distanceKm, calories, System.currentTimeMillis());
                                FitnessDatabaseHelper dbHelper = new FitnessDatabaseHelper(context);
                                dbHelper.saveFitnessData(data);
                                Toast.makeText(context, "Fitness data saved successfully.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }
}