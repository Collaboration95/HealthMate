package com.example.healthmate.social_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.healthmate.FYP_Adapter;
import com.example.healthmate.R;
import com.example.healthmate.social_postModel;
import com.example.healthmate.social_postModelHolder;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;


public class fragment_forYou extends Fragment {
    Button button;
    ArrayList<social_postModel> fyp_data= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view=  inflater.inflate(R.layout.fragment_for_you, container, false);

        RecyclerView recycle = null;
        recycle = view.findViewById(R.id.fyp_RecyclerView);
        social_postModel.populateDefault();
        FYP_Adapter adapter = new FYP_Adapter(getActivity(), social_postModelHolder.getInstance().getData());
        recycle.setAdapter(adapter);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


}