package com.example.healthmate;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

public class social extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    //    Variable to store context
    Context context = social.this;

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    Adapter_social_fragment socialAdapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
//
        tabLayout= findViewById(R.id.tab_layout);
        viewPager2 =findViewById(R.id.viewpager);
        socialAdapter=new Adapter_social_fragment(this);
        viewPager2.setAdapter(socialAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Objects.requireNonNull(tabLayout.getTabAt(position)).select();
            }


        });
//       Following Code block to be included in every activity to take care of bottom navigation view functionality
        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        Code to set current selected icon in bottomnavigation view
        bottomNavigationView.setSelectedItemId(R.id.social);

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
                        startActivity(new Intent( context, run.class  ));
                        return true;

                    case R.id.social:
                        Toast.makeText(context,"Social",Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.trends:
                        Toast.makeText(context,"Trends",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent( context, trend.class));
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
                        }

                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

}

