package com.example.healthmate.social_fragments;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthmate.FYP_Adapter;
import com.example.healthmate.R;
import com.example.healthmate.Social_PostModel;
import com.example.healthmate.Social_PostModelHolder;

import java.util.ArrayList;

/**
 This class represents the "For You" fragment in the social section of the app.
 It displays posts recommended for the user based on their interests and activity.
 */
public class Fragment_ForYou extends Fragment {
    // List of social post data
    ArrayList<Social_PostModel> fyp_data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_for_you, container, false);

        // Set up RecyclerView for displaying "For You" posts
        RecyclerView recycle = view.findViewById(R.id.fyp_RecyclerView);

        // Check if social posts data exists, populate with default data if none
        if (Social_PostModelHolder.getInstance().postCount() == 0) {
            Social_PostModel.populateDefault();
        } else {
            // Log number of existing social posts
            Log.d("Great Success",Social_PostModelHolder.getInstance().postCount() + "");
        }

        // Create adapter and set it to RecyclerView
        FYP_Adapter adapter = new FYP_Adapter(getActivity(), Social_PostModelHolder.getInstance().getData());
        recycle.setAdapter(adapter);

        // Set RecyclerView layout and animation
        recycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, true) {
            @Override
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        });

        // Return the view for the fragment
        return view;
    }
}