package com.example.healthmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    button = findViewById(R.id.button1);
    button.setOnClickListener(view -> {
        Toast.makeText(MainActivity.this,"THis is a message", Toast.LENGTH_LONG).show();
    });
    }
}