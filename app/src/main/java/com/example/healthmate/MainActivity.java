package com.example.healthmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    BottomNavigationView bottomNavigationView;
    calorie calorieFragment = new calorie();
    run runFragment = new run();
    social socialFragment = new social();
    trend trendFragment = new trend();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,calorieFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean   onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.calorie:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,calorieFragment).commit();
                        return true;

                    case R.id.run:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,runFragment).commit();
                        return true;

                    case R.id.social:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,socialFragment).commit();
                        return true;

                    case R.id.trends:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,trendFragment).commit();
                        return true;

                }

                return false;
            }
        });




    }

}