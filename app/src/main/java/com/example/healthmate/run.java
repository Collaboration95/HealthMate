package com.example.healthmate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class run extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Context context = run.this;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.run);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean   onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.calorie:
                        Toast.makeText(context,"Calorie",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent( context, MainActivity.class  ));
                        return true;

                    case R.id.run:
                        Toast.makeText(context,"Run",Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.social:
                        Toast.makeText(context,"Social",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent( context, social.class  ));
                        return true;

                    case R.id.trends:
                        Toast.makeText(run.this,"Trends",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent( context, trend.class  ));
                        return true;

                }
                return false;
            }
        });
    }

}

