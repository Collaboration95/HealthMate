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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
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
    Button updateData;
    Button suggestGoals;

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

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.calorie:
                        Toast.makeText(context, "Calorie", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, MainActivity.class));
                        return true;
                    case R.id.alltrends:
                        Toast.makeText(context, "Run", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, Run.class));
                        return true;
                    case R.id.social:
                        Toast.makeText(context, "Social", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, Social.class));
                        return true;
                    case R.id.goals:
                        Toast.makeText(context, "Trends", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
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
    public void populateData(){
        EditText userName = findViewById(R.id.editUserName);
        Spinner userSex = findViewById(R.id.editSex);
        EditText userWeight = findViewById(R.id.editWeight);
        EditText userHeight = findViewById(R.id.editHeight);
        EditText userCalGoal = findViewById(R.id.editIntakeGoal);
        EditText userWorkoutGoal = findViewById(R.id.editWorkoutGoal);
        // Get an instance of the userData singleton class
//        UserData userData = UserData.getInstance();

// Load default values from userData singleton class
//        userName.setText(userData.getUserName());
//        userWeight.setText(String.valueOf(userData.getWeight()));
//        userHeight.setText(String.valueOf(userData.getHeight()));
//        userCalGoal.setText(String.valueOf(userData.getCalorieIntakeGoal()));
//        userWorkoutGoal.setText(String.valueOf(userData.getWorkoutGoal()));
//
//// Set default value for userSex
//        int sexIndex = userData.getSex(); // Assuming getUserSex() returns an index
//        userSex.setSelection(sexIndex);

    }
}


