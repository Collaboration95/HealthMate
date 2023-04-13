package com.example.healthmate;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Objects;

public class Social extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    // Variable to store context for later use
    Context context = Social.this;

    // Declare UI components for the social activity
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    Adapter_Social_Fragment socialAdapter;
    Button shareButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        // Initialize the tab layout and view pager components
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.viewpager);
        socialAdapter = new Adapter_Social_Fragment(this);
        viewPager2.setAdapter(socialAdapter);

        // Set up the shareButton to share posts
//        shareButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                shareToSocialMedia();
//            }
//        });

        // Set up the tab layout and view pager to work together
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
                TabLayout.Tab tab = tabLayout.getTabAt(position);
                if (tab != null) {
                    tab.select();
                }
            }
        });

        // Set up the bottom navigation view for switching between activities
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.social);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.calorie:
                        startActivity(new Intent(context, MainActivity.class));
                        return true;

                    case R.id.alltrends:
                        startActivity(new Intent(context, Run.class));
                        return true;

                    case R.id.social:
                        return true;

                    case R.id.goals:
                        startActivity(new Intent(context, Trend.class));
                        return true;
                }
                return false;
            }
        });

        // Set up the card view for displaying a popup menu
        CardView cardView = findViewById(R.id.bringtoFront);
        cardView.bringToFront();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a popup menu and set a listener for menu item clicks
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.newMeal) {
                            // Start the NewMeal activity when the "newMeal" menu item is clicked
                            startActivity(new Intent(context, NewMeal.class));
                        } else if (item.getItemId() == R.id.newRun) {
                            // Start the MapsActivity when the "newRun" menu item is clicked
                            startActivity(new Intent(context, MapsActivity.class));
                        }
                        else if (item.getItemId()==R.id.newPost){
                            startActivity(new Intent(context, NewPost.class));
                        }
                        return true;
                    }
                });
                // Show the popup menu
                popupMenu.show();
            }
        });
    }




}
