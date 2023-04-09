package com.example.healthmate.social_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.healthmate.FYP_Adapter;
import com.example.healthmate.R;
import com.example.healthmate.social_postModel;
import com.example.healthmate.social_postModelHolder2;

import java.util.ArrayList;


public class fragment_following extends Fragment {
    Button button;
    ArrayList<social_postModel> following= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view=  inflater.inflate(R.layout.fragment_following, container, false);

        RecyclerView recycle = null;
        recycle = view.findViewById(R.id.fyp_RecyclerView);
        social_postModel.populateDefault2();
        FYP_Adapter adapter = new FYP_Adapter(getActivity(), social_postModelHolder2.getInstance().getData());
        recycle.setAdapter(adapter);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


}