package com.example.healthmate;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NewPost extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);
        Button cancel = findViewById(R.id.cancel_button);
        Button add = findViewById(R.id.add_meal);


        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                EditText runName = findViewById(R.id.editRunName);
                EditText runDistance = findViewById(R.id.editRunDistance);
                EditText runTime = findViewById(R.id.editRunTime);

                String name = runName.getText().toString();
                String distance = runDistance.getText().toString();
                String time = runTime.getText().toString();
                if (!name.isEmpty() && !distance.isEmpty() && !time.isEmpty()) {
                    setUpPost(name, distance, time);
                    startActivity(new Intent(NewPost.this,social.class));
                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewPost.this, social.class));
            }
        });
    }

    private void setUpPost(String name, String distance, String time) {
        social_postModelHolder.getInstance().storePost(new social_postModel("Guru",name , distance,time,"0"));
    }
}