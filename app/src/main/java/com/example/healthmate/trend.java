package com.example.healthmate;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class trend extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean   onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.calorie:
                        Toast.makeText(trend.this,"Calorie",Toast.LENGTH_SHORT).show();
                        intent = new Intent( trend.this, MainActivity.class  );
                        startActivity(intent);
                        return true;

                    case R.id.run:
                        Toast.makeText(trend.this,"Run",Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.social:
                        Toast.makeText(trend.this,"Social",Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.trends:
                        Toast.makeText(trend.this,"Trends",Toast.LENGTH_SHORT).show();
                        intent= new Intent( trend.this, trend.class);
                        startActivity(intent);
                        return true;

                }

                return false;
            }
        });
    }
}
