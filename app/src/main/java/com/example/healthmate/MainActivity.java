
package com.example.healthmate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.Field;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This activity serves as the main hub of the Fitness Tracker App, providing a
 * central access point to the Calorie Counter, Google Maps API, and Socials Page.
 * The user interface displays the total calorie count, macro breakdown, and
 * fitness statistics. The bottom navigation allows users to switch between
 * different pages within the app.
 */
public class MainActivity extends AppCompatActivity implements MyObserver {
    // UI components
    private final Context context = MainActivity.this;
    private BottomNavigationView bottomNavigationView;
    private CircularProgressIndicator circularProgressIndicator;
    private MealAdapter adapter;
    private SharedPreferences sharedPrefsMeal;
    private static final String SHARED_PREFS_NAME_MEAL = "MealPrefs";
    private SharedPreferences sharedPrefs;
    private static final String SHARED_PREFS_NAME = "UserDataPrefs";

    // GoogleFitManager instance for managing Google Fit data
    private GoogleFitManager googleFitManager;

    /**
     * onCreate initializes the activity, setting up UI components and
     * the GoogleFitManager instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPrefs = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        sharedPrefsMeal= getSharedPreferences(SHARED_PREFS_NAME_MEAL,MODE_PRIVATE);
        // Load user data from shared preferences
        loadUserData();

        // Standard activity initialization
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recycler view thingy
        googleFitManager = new GoogleFitManager(this, MainActivity.this );
        googleFitManager.requestGoogleFitPermissions();

        // Store the GoogleFitManager instance in the singleton
        AppManager.getInstance().setGoogleFitManager(googleFitManager);

        // Initialize RecyclerView for displaying meal data
        ArrayList<Plus_AddMeal> data = NewMealHolder.getInstance().getData();
        RecyclerView recyclerView = findViewById(R.id.Mealrecyclerview);
        adapter = new MealAdapter(this, data);

        if (data.size() > 0) {
            recyclerView.setAdapter(new MealAdapter(this, data));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        // Register MainActivity as an observer for meal data updates
        NewMealHolder.getInstance().addObserver(this);

        // Update the UI to reflect current data
        updateUI();

        // Set up event listeners for UI components
        setupListeners();

        // Code to update UI elements
        Intent intent2 = getIntent();
        if (intent2 != null && intent2.getAction() != null && intent2.getAction().equals("com.example.myapp.ACTION_CALL_FUNCTION")) {
            updateUI();
        }
    }

    /**
     * setupListeners sets up event listeners for UI components like
     * the bottom navigation and popup menu.
     */
    private void setupListeners() {
        // Setup event listener for the "add" button
        CardView cardView = findViewById(R.id.bringtoFront);
        cardView.bringToFront();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the popup menu when the button is clicked
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this, "Selected: " + item.getTitle(), Toast.LENGTH_SHORT).show();

                        // Handle menu item clicks and start the appropriate activity
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
        // Setup event listener for the bottom navigation view
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle bottom navigation item clicks and start the appropriate activity
                switch (item.getItemId()) {
                    case R.id.calorie:
                        Log.d("Great Success", NewMealHolder.getInstance().getMealCount() + "");
                        return true;

                    case R.id.alltrends:

                        startActivity(new Intent(context, Run.class));
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
    }

    /**
     * OnChange method is called when meal data updates
     */
    @Override
    public void OnChange() {
        updateUI();
    }

    /**
     * updateUI updates the UI components to reflect the current data.
     */
    public void updateUI() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Find and initialize UI elements
                CircularProgressIndicator circularProgressIndicator = findViewById(R.id.circularProgressIndicator);
                TextView calorieConsumed = findViewById(R.id.textCalorie);
                TextView totalCal =findViewById(R.id.totalCalorie);
                ProgressBar progressBarProtein = findViewById(R.id.proteinProgress);
                ProgressBar progressBarFat = findViewById(R.id.fatProgress);
                ProgressBar progressBarCarbs = findViewById(R.id.CarbProgress);

                // Calculate macro nutrient totals
                int totalProtein = NewMealHolder.getInstance().TotalProtein() * 4;
                int totalFat = NewMealHolder.getInstance().TotalFat() * 9;
                int totalCarbs = NewMealHolder.getInstance().TotalCarbs() * 4;
                int totalFood = totalCarbs + totalFat + totalProtein;

                // Update progress bars with data from newMealHolder
                if (totalFood != 0) {
                    if (progressBarProtein != null && progressBarFat != null && progressBarCarbs != null) {
                        progressBarFat.setProgress((totalFat * 100) / totalFood);
                        progressBarCarbs.setProgress((totalCarbs * 100) / totalFood);
                        progressBarProtein.setProgress((totalProtein * 100) / totalFood);
                    }
                }

                // Update the circular progress indicator with the total calorie count
                if (circularProgressIndicator != null) {
                    int progress =((NewMealHolder.getInstance().TotalCalories()*100)/UserDataSingleton.getInstance().getUserData().getCalorieIntakeGoal());
                    Log.d("UpdateUi Great Success",progress+" ");
                    circularProgressIndicator.setProgress(progress, true);
                }

                totalCal.setText(String.format("of %d kcal",UserDataSingleton.getInstance().getUserData().getCalorieIntakeGoal()));
                // Update the total calorie text with data from newMealHolder
                if (calorieConsumed != null) {
                    calorieConsumed.setText(NewMealHolder.getInstance().TotalCalories() + " ");
                }
            }
        });
    }

    /**
     * onActivityResult handles the result of the Google Fit permission request.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (googleFitManager.handleActivityResult(requestCode, resultCode, data)) {
            // Permissions granted, you can access Google Fit API data here.
            fetchDataAndUpdateUI();
        } else {
      // When user is not signed
        Toast.makeText(context,"Please Sign in ",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * fetchDataAndUpdateUI fetches the latest Google Fit data and updates the UI accordingly.
     */
    private void fetchDataAndUpdateUI() {
        googleFitManager.getTodayStepCount(new GoogleFitManager.OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                int stepCount = dataPoint.getValue(Field.FIELD_STEPS).asInt();
                // Update your UI with the step count
            }
        });

        googleFitManager.getTodayDistance(new GoogleFitManager.OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                float distance = dataPoint.getValue(Field.FIELD_DISTANCE).asFloat();
                // Update your UI with the distance
            }
        });

        googleFitManager.getTodayCaloriesBurned(new GoogleFitManager.OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                float caloriesBurned = dataPoint.getValue(Field.FIELD_CALORIES).asFloat();
                // Update your UI with the calories burned
            }
        });

        googleFitManager.getLatestHeartRate(new GoogleFitManager.OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                float heartRate = dataPoint.getValue(Field.FIELD_BPM).asFloat();
                // Update your UI with the latest heart rate
            }
        });
    }

    private void saveUserData() {
        UserData userData = UserDataSingleton.getInstance().getUserData();
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("userName", userData.getUserName());
        editor.putInt("sex", userData.getSex());
        editor.putInt("weight", userData.getWeight());
        editor.putInt("height", userData.getHeight());
        editor.putInt("calorie_intake_goal", userData.getCalorieIntakeGoal());
        editor.putInt("workoutGoal", userData.getWorkoutGoal());
        editor.putBoolean("isDefault", userData.isDefault());
        editor.apply();

        Log.d("Process Destroyed","Data saved");
    }

    // Method to load user data from shared preferences
    private void loadUserData() {
        String userName = sharedPrefs.getString("userName", "Guru");
        int sex = sharedPrefs.getInt("sex", 2);
        int weight = sharedPrefs.getInt("weight", 85);
        int height = sharedPrefs.getInt("height", 183);
        int calorie_intake_goal = sharedPrefs.getInt("calorie_intake_goal", 2400);
        int workoutGoal = sharedPrefs.getInt("workoutGoal", 2900);
        boolean isDefault = sharedPrefs.getBoolean("isDefault", true);
        UserData temp = new UserData();
        temp.updateData(userName, sex, weight, height, calorie_intake_goal, workoutGoal);
        Log.d("This is getting called","LoadUserData");
        UserDataSingleton.getInstance().setUserData(temp);

        Gson gson = new Gson();

        // Retrieve the central data from SharedPreferences
        String json = sharedPrefsMeal.getString("central_data", "");
        Type type = new TypeToken<ArrayList<Plus_AddMeal>>() {}.getType();
        ArrayList<Plus_AddMeal>central_data = gson.fromJson(json, type);
        if (central_data!=null){
            Log.e("LoadUserData ","length of datastore"+central_data.size());
            NewMealHolder.getInstance().deleteData();
            for (Plus_AddMeal var: central_data){
                NewMealHolder.getInstance().storeMeal(var);
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Save user data to shared preferences when the activity is destroyed
        saveUserData();
    }
}
