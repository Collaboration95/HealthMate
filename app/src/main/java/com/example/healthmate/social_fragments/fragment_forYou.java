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
        setUpPostModel();
        FYP_Adapter adapter = new FYP_Adapter(getActivity(),fyp_data);
        recycle.setAdapter(adapter);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private void setUpPostModel(){
            String[] Name = {"John Smith", "John Travolta", "Sarah Johnson", "Michael Brown", "Rachel Lee", "David Chen", "Emily Taylor"};
            String[] activityName = {"Evening Jog", "Morning Jog", "Hiking", "Biking", "Swimming", "Yoga", "Pilates"};
            String[] Likes = {"12", "10", "8", "5", "17", "13", "9"};
            String[] Distance = {"1.23km", "2.3km", "5.1km", "2.7km", "3.8km", "4.2km", "6.5km"};
            String[] Time = {"10m", "12m", "30m", "25m", "50m", "42m", "55m"};

        int Image = R.drawable.ic_launcher_background;
            int AddFriendicon =R.drawable.ic_launcher_addfriend;
            int activityIcon=R.drawable.ic_launcher_rightsymbol;
            int likeIcon=R.drawable.ic_launcher_addfriend;
            int timeIcon=R.drawable.ic_launcher_timer;
            int distanceIcon=R.drawable.ic_launcher_distance;
            for (int i =0;i<Name.length;i++){
                fyp_data.add(new social_postModel(Name[i],activityName[i],Distance[i],Time[i],Likes[i],AddFriendicon,activityIcon,likeIcon,timeIcon,distanceIcon,Image));
            }
    }
}