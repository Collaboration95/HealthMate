package com.example.healthmate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;


/**
The Trend class represents the Goals page in the HealthMate app.
 It has an instance variable bottomNavigationView to manage the bottom navigation view and a
Context variable to store the current context. The class also has an ArrayList to store the data for the list of trends.
 **/
public class Trend extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Intent intent;
    private SharedPreferences sharedPrefs;
    private static final String SHARED_PREFS_NAME = "UserDataPrefs";
    Context context = Trend.this;
    Button updateData;
    Button suggestGoals;

    /**
     * onCreate method sets up the necessary views and event listeners for the Trend class.
     *
     * @param savedInstanceState Bundle object to save any previously saved state
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend);

        populateData();

        // Set up bottom navigation view
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.goals);
        Spinner spinner = findViewById(R.id.editSex);

        // Create an ArrayAdapter using a string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_options, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Set the default selected item to the second option in the array
        spinner.setSelection(0);

        // Set up bottom navigation view onItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.calorie:
                        startActivity(new Intent(context, MainActivity.class));
                        return true;
                    case R.id.alltrends:
                        startActivity(new Intent(context, Run.class));
                        return true;
                    case R.id.social:
                        startActivity(new Intent(context, Social.class));
                        return true;
                    case R.id.goals:
                        return true;
                }
                return false;
            }
        });

        // Set up "Suggest Goals" button click event
        suggestGoals= findViewById(R.id.suggestGoal);
        suggestGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userSexData = ((Spinner) findViewById(R.id.editSex)).getSelectedItemPosition();
                int userWeight = Integer.parseInt(((EditText) findViewById(R.id.editWeight)).getText().toString());
                int userHeight = Integer.parseInt(((EditText) findViewById(R.id.editHeight)).getText().toString());
                EditText userCalGoal = findViewById(R.id.editIntakeGoal);
                EditText userWorkoutGoal = findViewById(R.id.editWorkoutGoal);
                int[] suggestedData = suggestGoal(userSexData, userWeight, userHeight);
                Snackbar snackbar = Snackbar
                        .make(view, "Suggested Calorie Intake: "+suggestedData[1]+"\n"+"Suggested Workout Goal: "+suggestedData[0], Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        // Set up "Update Data" button click event
        updateData = findViewById(R.id.updateData);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userName = findViewById(R.id.editUserName);
                Spinner userSex = findViewById(R.id.editSex);
                EditText userWeight = findViewById(R.id.editWeight);
                EditText userHeight = findViewById(R.id.editHeight);
                EditText userCalGoal = findViewById(R.id.editIntakeGoal);
                EditText userWorkoutGoal = findViewById(R.id.editWorkoutGoal);

                if (TextUtils.isEmpty(userName.getText()) || userSex.getSelectedItemPosition() == 0 ||
                        TextUtils.isEmpty(userWeight.getText()) || TextUtils.isEmpty(userHeight.getText()) ||
                        TextUtils.isEmpty(userCalGoal.getText()) || TextUtils.isEmpty(userWorkoutGoal.getText())) {
                    Log.d("UpdateData", "One or more fields are empty");
                    return;
                }
                else if(checkIrregularInputs(Integer.parseInt(userWeight.getText().toString()),
                        Integer.parseInt(userHeight.getText().toString()),
                        Integer.parseInt(userCalGoal.getText().toString()),
                        Integer.parseInt(userWorkoutGoal.getText().toString()))){
                    Toast.makeText(Trend.this,"Please Check Inputs",Toast.LENGTH_SHORT).show();
                }

                UserData changed = new UserData();
                changed.updateData(
                        userName.getText().toString(),
                        (int) userSex.getSelectedItemPosition(),
                        Integer.parseInt(userWeight.getText().toString()),
                        Integer.parseInt(userHeight.getText().toString()),
                        Integer.parseInt(userCalGoal.getText().toString()),
                        Integer.parseInt(userWorkoutGoal.getText().toString())
                );

                UserData userData1 = UserDataSingleton.getInstance().getUserData();
                // set the fields of userData2 to be compared with userData1

                int comparisonResult = UserDataSingleton.getInstance().userDataComparator.compare(userData1, changed);

                if (comparisonResult != 0) {
                    UserDataSingleton.getInstance().setUserData(changed);
                    Log.d("UpdateData Great Success",UserDataSingleton.getInstance().toString());

//                   Update UI elements of MainActivity
                    Intent intent = new Intent("com.example.myapp.ACTION_CALL_FUNCTION");
                    startActivity(intent);

                } else {
                    Log.d("UpdateData ","No changes made");
                }
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

/**
 * Check the input fields for irregular inputs.
 * @param weight The weight
 * @param height The height
 * @param calorieintake The calorie intake goal
 * @param workout The workout goal
 * @return true if any input field is irregular, false otherwise
 **/
    private boolean checkIrregularInputs( int weight, int height, int calorieintake, int workout) {
        return weight <= 250 && weight >= 20 && height >= 100 && height <= 250 && calorieintake >= 500 && calorieintake <= 5000 && !(workout > calorieintake * 1.5);
    }

    /**
     * Populate the input fields with the current user data.
     */
    public void populateData(){
        EditText userName = findViewById(R.id.editUserName);
        Spinner userSex = findViewById(R.id.editSex);
        EditText userWeight = findViewById(R.id.editWeight);
        EditText userHeight = findViewById(R.id.editHeight);
        EditText userCalGoal = findViewById(R.id.editIntakeGoal);
        EditText userWorkoutGoal = findViewById(R.id.editWorkoutGoal);

        UserData userData = UserDataSingleton.getInstance().getUserData();

        // Load default values from userData singleton class
        userName.setText(userData.getUserName());
        userWeight.setText(String.valueOf(userData.getWeight()));
        userHeight.setText(String.valueOf(userData.getHeight()));
        userCalGoal.setText(String.valueOf(userData.getCalorieIntakeGoal()));
        userWorkoutGoal.setText(String.valueOf(userData.getWorkoutGoal()));

        // Set default value for userSex
        int sexIndex = userData.getSex(); // Assuming getUserSex() returns an index
        userSex.setSelection(sexIndex);
    }

    /**
     * Suggest a goal for the user based on their sex, weight, and height.
     * @param userSex The sex of the user
     * @param userWeight The weight of the user
     * @param userHeight The height of the user
     * @return An array containing the suggested workout and calorie intake goals
     */
    public int[] suggestGoal(int userSex, double userWeight, double userHeight ) {
        double BMR;
        double activityFactor = 1.55; // Assume moderately active level for all users
        double calorieDeficit = 7700; // Create a calorie deficit of 7700 calories per month to lose 1 kg of body weight
        int userAge =24; // Constant for now

        // Calculate BMR based on user sex
        if (userSex%2==1) {
            BMR = 10 * userWeight + 6.25 * userHeight - 5 * userAge + 5; // Mifflin-St Jeor Equation
            //BMR = 13.397 * userWeight + 4.799 * userHeight - 5.677 * userAge + 88.362; // Revised Harris-Benedict Equation
            //BMR = 370 + 21.6 * (1 - getBodyFatPercentage()) * userWeight; // Katch-McArdle Formula
        } else {
            BMR = 10 * userWeight + 6.25 * userHeight - 5 * userAge - 161; // Mifflin-St Jeor Equation
            //BMR = 9.247 * userWeight + 3.098 * userHeight - 4.330 * userAge + 447.593; // Revised Harris-Benedict Equation
            //BMR = 370 + 21.6 * (1 - getBodyFatPercentage()) * userWeight; // Katch-McArdle Formula
        }

        // Calculate recommended daily calorie intake and burn
        double maintenanceCalories = BMR * activityFactor;
        double targetCalories = maintenanceCalories - (calorieDeficit / 30); // Create a calorie deficit of 7700 calories per month to lose 1 kg of body weight
        double calorieBurn = targetCalories;
        return new int[] { roundToNearest100(maintenanceCalories),roundToNearest100(targetCalories) };
    }

    /**
     * Round a given number to the nearest hundred.
     * @param num The number to round
     * @return The rounded number
     */
    public static int roundToNearest100(double num) {
        int roundedNum = (int) Math.round(num / 100.0) * 100;
        return roundedNum;
    }

/**
 * * Save the user data to SharedPreferences when the app is paused, stopped or destroyed.
 * */
    private void saveUserData() {
        UserData userData = UserDataSingleton.getInstance().getUserData();
        sharedPrefs = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
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

    /**
     * Override the onPause method to save user data.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveUserData();
    }

    /**
     * Override the onDestroy method to save user data.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveUserData();
    }

    /**
     * Override the onStop method to save user data.
     */
    @Override
    protected void onStop() {
        super.onStop();
        saveUserData();
    }
}


