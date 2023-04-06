package com.example.healthmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FYP_Adapter extends RecyclerView.Adapter<FYP_Adapter.myViewHolder>{

    Context context;
    ArrayList<social_postModel> data;
    public FYP_Adapter(Context context, ArrayList<social_postModel> data) {
        this.context = context;
        this.data = data;

    }

    @NonNull
    @Override
    public FYP_Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fyp_recyclerelement,parent,false);
        return new FYP_Adapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FYP_Adapter.myViewHolder holder, int position) {
        // Set the text for the TextViews
        holder.Name.setText(data.get(position).getName());
        holder.activityName.setText(data.get(position).getActivityName());
        holder.Distance.setText(data.get(position).getDistance());
        holder.Time.setText(data.get(position).getTime());
        holder.likes.setText(data.get(position).getLikes());

// Set the image resources for the ImageViews
        holder.Image.setImageResource(data.get(position).getImage());
        holder.AddFriendicon.setImageResource(data.get(position).getAddFriendicon());
        holder.activityIcon.setImageResource(data.get(position).getActivityIcon());
        holder.likeIcon.setImageResource(data.get(position).getLikeIcon());
        holder.timeIcon.setImageResource(data.get(position).getTimeIcon());
        holder.distanceIcon.setImageResource(data.get(position).getDistanceIcon());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView Name;
        TextView activityName;
        TextView Distance;
        TextView Time;
        TextView likes;
        ImageView Image;
        ImageView AddFriendicon;
        ImageView activityIcon;
        ImageView likeIcon;
        ImageView timeIcon;
        ImageView distanceIcon;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            Image = itemView.findViewById(R.id.MainImage);
            Name = itemView.findViewById(R.id.Name);
            activityName = itemView.findViewById(R.id.activityName);
            Distance = itemView.findViewById(R.id.Distance);
            Time = itemView.findViewById(R.id.Time);
            likes = itemView.findViewById(R.id.Likes);
            AddFriendicon = itemView.findViewById(R.id.addFriend);
            activityIcon = itemView.findViewById(R.id.iconArrow);
            likeIcon = itemView.findViewById(R.id.iconLike);
            timeIcon = itemView.findViewById(R.id.iconTime);
            distanceIcon = itemView.findViewById(R.id.iconDistance);
        }
    }
}
