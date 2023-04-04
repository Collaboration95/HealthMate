package com.example.healthmate;

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

public class trend extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Intent intent;
    ArrayList<trend_class> data = new ArrayList<>();
    private void setUpData(){
        String[] activityName = {"Move","Exercise","Move","Distance","RunningPace","Walking Pace"};
        String[] activityNumber = {"1268","1284","1284","1284","1284","1284"};
        String[] activityUnit= {"Kcal/day","Kcal/day","Kcal/day","Kcal/day","Kcal/day","Kcal/day"};
        for(int i=0;i<activityUnit.length;i++){
            data.add(new trend_class(activityName[i],activityNumber[i],activityUnit[i],R.drawable.ic_launcher_add));
        }
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend);
        RecyclerView recycle = findViewById(R.id.trend_recyler);
        setUpData();
        trend_Adapter adapter = new trend_Adapter(this,data);
        recycle.setAdapter(adapter);
        recycle.setLayoutManager(new LinearLayoutManager(this));


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
