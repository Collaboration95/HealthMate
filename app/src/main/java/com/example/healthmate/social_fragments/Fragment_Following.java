/**

 This class represents the "Following" fragment in the social section of the app.
 It displays posts from the accounts that the user is following.
 */
package com.example.healthmate.social_fragments;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthmate.FYP_Adapter;
import com.example.healthmate.R;
import com.example.healthmate.Social_PostModel;
import com.example.healthmate.Social_PostModelHolder2;

import java.util.ArrayList;

public class Fragment_Following extends Fragment {
    // List of following post data
    ArrayList<Social_PostModel> following = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_following, container, false);

        // Set up RecyclerView for displaying following posts
        RecyclerView recycle = view.findViewById(R.id.fyp_RecyclerView);

        // Check if following posts data exists, populate with default data if none
        if (Social_PostModelHolder2.getInstance().postCount() == 0) {
            Social_PostModel.populateDefault2();
        }

        // Create adapter and set it to RecyclerView
        FYP_Adapter adapter = new FYP_Adapter(getActivity(), Social_PostModelHolder2.getInstance().getData());
        recycle.setAdapter(adapter);

        // Set RecyclerView layout
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Return the view for the fragment
        return view;
    }
}