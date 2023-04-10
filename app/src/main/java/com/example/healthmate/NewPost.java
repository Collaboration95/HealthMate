package com.example.healthmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * NewPost activity allows users to create and add new posts to the Social activity.
 */
public class NewPost extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);

        // Initialize buttons
        Button cancel = findViewById(R.id.cancel_button);
        Button add = findViewById(R.id.add_meal);

        // Set onClickListener for the add button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get input values from the EditText fields
                EditText runName = findViewById(R.id.editRunName);
                EditText runDistance = findViewById(R.id.editRunDistance);
                EditText runTime = findViewById(R.id.editRunTime);

                // Convert EditText values to strings
                String name = runName.getText().toString();
                String distance = runDistance.getText().toString();
                String time = runTime.getText().toString();

                // Check if the input fields are not empty
                if (!name.isEmpty() && !distance.isEmpty() && !time.isEmpty()) {
                    // Set up the post with the input values
                    setUpPost(name, distance, time);
                    // Redirect to the Social activity
                    startActivity(new Intent(NewPost.this, Social.class));
                }
            }
        });

        // Set onClickListener for the cancel button
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to the Social activity
                startActivity(new Intent(NewPost.this, Social.class));
            }
        });
    }

    /**
     * Sets up the post using the input values and stores it in the Social_PostModelHolder.
     *
     * @param name     The name of the run.
     * @param distance The distance of the run.
     * @param time     The time of the run.
     */
    private void setUpPost(String name, String distance, String time) {
        Social_PostModelHolder.getInstance().storePost(new Social_PostModel("Guru", name, distance, time, "0"));
    }
}
