package com.example.healthmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button button;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean   onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.calorie:
                        Toast.makeText(MainActivity.this,"Calorie",Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.run:
                        Toast.makeText(MainActivity.this,"Run",Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.social:
                        Toast.makeText(MainActivity.this,"Social",Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.trends:
                        Toast.makeText(MainActivity.this,"Trends",Toast.LENGTH_SHORT).show();
                        return true;

                }

                return false;
            }
        });




    }

}