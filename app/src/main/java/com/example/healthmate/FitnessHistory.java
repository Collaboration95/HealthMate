package com.example.healthmate;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FitnessHistory extends AppCompatActivity {

    private FitnessDatabaseHelper fitnessDatabaseHelper;
    private RecyclerView recyclerView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_history);

        recyclerView = findViewById(R.id.fitness_data_recyclerview);
        backButton = findViewById(R.id.back_button);

        fitnessDatabaseHelper = new FitnessDatabaseHelper(this);
        List<FitnessData> fitnessDataList = fitnessDatabaseHelper.getAllFitnessData();
        FitnessDataAdapter fitnessDataAdapter = new FitnessDataAdapter(fitnessDataList);
        recyclerView.setAdapter(fitnessDataAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
