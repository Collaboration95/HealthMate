package com.example.healthmate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * This class represents the FitnessHistory Activity, which displays the historical
 * fitness data stored in the HealthMate application.
 */
public class FitnessHistory extends AppCompatActivity {

    // Instance variables for UI components and the database helper
    private FitnessDatabaseHelper fitnessDatabaseHelper;
    private RecyclerView recyclerView;
    private Button backButton;

    /**
     * Called when the FitnessHistory Activity is created.
     * @param savedInstanceState A Bundle object containing the previously saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_history);

        // Initialize UI components
        recyclerView = findViewById(R.id.fitness_data_recyclerview);
        backButton = findViewById(R.id.back_button);

        // Initialize the database helper and retrieve the fitness data
        fitnessDatabaseHelper = new FitnessDatabaseHelper(this);
        List<FitnessData> fitnessDataList = fitnessDatabaseHelper.getAllFitnessData();

        // Set up the RecyclerView with the FitnessDataAdapter
        FitnessDataAdapter fitnessDataAdapter = new FitnessDataAdapter(fitnessDataList);
        recyclerView.setAdapter(fitnessDataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set the click listener for the back button to navigate back to the previous activity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
