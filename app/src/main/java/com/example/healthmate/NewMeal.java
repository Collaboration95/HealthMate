package com.example.healthmate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This class represents the NewMeal activity, allowing users to input
 * meal data and add it to the list of meals stored in the application.
 */
public class NewMeal extends AppCompatActivity {
    private SharedPreferences sharedPrefs;
    private static final String SHARED_PREFS_NAME = "MealPrefs";
    /**
     * Set up a new meal with the provided information and store it.
     */
    private void setupMeal(String mealName, String proteinText, String fatText, String carbsText, String caloriesText, String timeText) {
        NewMealHolder.getInstance().storeMeal(new Plus_AddMeal(mealName, proteinText, fatText, carbsText, caloriesText, timeText));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        sharedPrefs = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmeal);

        Button cancel = findViewById(R.id.cancel_button);


        // Set up the "add" button to add a new meal when clicked
        Button add = findViewById(R.id.add_meal);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve input values from the form
                EditText editMealName = findViewById(R.id.editUserName);
                EditText protein = findViewById(R.id.editSex);
                EditText fat = findViewById(R.id.editWeight);
                EditText carbs = findViewById(R.id.editHeight);
                EditText totalcalories = findViewById(R.id.editIntakeGoal);
                EditText time = findViewById(R.id.editWorkoutGoal);
                String mealName = editMealName.getText().toString();
                String proteinText = protein.getText().toString();
                String fatText = fat.getText().toString();
                String carbsText = carbs.getText().toString();
                String caloriesText = totalcalories.getText().toString();
                String timeText = time.getText().toString();

                // Ensure all fields have values before setting up the meal
                if (!mealName.isEmpty() && !proteinText.isEmpty() && !fatText.isEmpty() &&
                        !carbsText.isEmpty() && !caloriesText.isEmpty() && !timeText.isEmpty()) {
                    setupMeal(mealName, proteinText, fatText, carbsText, caloriesText, timeText);

                    Log.d("Great Sucess", NewMealHolder.getInstance().TotalCalories() + " ");
                    startActivity(new Intent(NewMeal.this, MainActivity.class).putExtra("Key", NewMealHolder.getInstance().TotalCalories() + " "));
                } else {
                    Toast.makeText(NewMeal.this, "Great Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set up the "cancel" button to display the current size of the central_data ArrayList
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewMeal.this, "Meal Add Cancelled", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(NewMeal.this,MainActivity.class));
            }
        });
    }

    private void saveUserData() {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Log.d("SaveUserData NewMeal",NewMealHolder.getInstance().getMealCount()+" ");
        // Save the central data to SharedPreferences as a JSON string
        Gson gson = new Gson();
        String json = gson.toJson(NewMealHolder.getInstance().getData());
        editor.putString("central_data", json);
        editor.apply();
        Log.d("Data saved","NewMeal");
        tempLoadData();
    }
    private void tempLoadData(){
        Gson gson = new Gson();

        // Retrieve the central data from SharedPreferences
        String json = sharedPrefs.getString("central_data", "");
        Type type = new TypeToken<ArrayList<Plus_AddMeal>>() {}.getType();
        ArrayList<Plus_AddMeal>central_data = gson.fromJson(json, type);
        if(central_data!=null){
            Log.d("tempLoadData",central_data.size()+" ");
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        saveUserData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveUserData();
    }
}
