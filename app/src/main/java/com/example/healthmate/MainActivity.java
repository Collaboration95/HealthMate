package com.example.healthmate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button button;
    //    Variable to store context
    Context context = MainActivity.this;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView cardView = findViewById(R.id.plusCard); // Replace "my_button_id" with your actual button ID
        cardView.bringToFront();
        cardView = findViewById(R.id.plusCard);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                        popupMenu.inflate(R.menu.popup_menu);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                Toast.makeText(MainActivity.this, "Selected: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                                if(item.getItemId()==R.id.newMeal){
                                    startActivity(new Intent(context,NewMeal.class));
                                }

                                return true;
                            }
                        });
                        popupMenu.show();
                    }
                });




//       Following Code block to be included in every activity to take care of bottom navigation view functionality
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean   onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.calorie:
                        Toast.makeText(context,"Calorie",Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.run:
                        Toast.makeText(context,"Run",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, run.class));
                        return true;

                    case R.id.social:
                        Toast.makeText(context,"Social",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, social.class));
                        return true;

                    case R.id.trends:
                        Toast.makeText(context,"Trends",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, trend.class));
                        return true;
                }
                return false;
            }
        });




    }




}