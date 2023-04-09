package com.example.healthmate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class trend extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Intent intent;
    Context context=trend.this ;
//    Variable to store context
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


//       Following Code block to be included in every activity to take care of bottom navigation view functionality
        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        Code to set current selected icon in bottomnavigation view
        bottomNavigationView.setSelectedItemId(R.id.trends);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.calorie:
                        Toast.makeText(context,"Calorie",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent( context, MainActivity.class));
                        return true;

                    case R.id.run:
                        Toast.makeText(context,"Run",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent( context, run.class));

                        return true;

                    case R.id.social:
                        Toast.makeText(context,"Social",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent( context, social.class));
                        return true;

                    case R.id.trends:
                        Toast.makeText(context,"Trends",Toast.LENGTH_SHORT).show();
                        return true;

                }
                return false;
            }
        });


        CardView cardView = findViewById(R.id.bringtoFront);
        cardView.bringToFront();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(context, "Selected: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        if(item.getItemId()==R.id.newMeal){
                            startActivity(new Intent(context,NewMeal.class));
                        }else if (item.getItemId()==R.id.newRun) {
                            startActivity(new Intent(context, MapsActivity.class));
                        }

                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

}

