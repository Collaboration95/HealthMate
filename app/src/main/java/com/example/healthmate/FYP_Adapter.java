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

/**
 * FYP_Adapter is the adapter for the RecyclerView in the social section of the app.
 * It displays social posts in a list format.
 */
public class FYP_Adapter extends RecyclerView.Adapter<FYP_Adapter.myViewHolder> {
    Context context;
    ArrayList<Social_PostModel> data;

    /**
     * Constructor that takes in the context and social post data.
     *
     * @param context the application context
     * @param data    an ArrayList of Social_PostModel objects
     */
    public FYP_Adapter(Context context, ArrayList<Social_PostModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public FYP_Adapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the RecyclerView element layout
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fyp_recyclerelement, parent, false);

        // Return a new ViewHolder for the layout
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


        int profileIcon = R.drawable.ic_launcher_person;
        int activityIcon = R.drawable.ic_launcher_rightsymbol;
        int likeIcon = R.drawable.ic_launcher_thumbsup;
        int timeIcon = R.drawable.ic_launcher_timer;
        int distanceIcon = R.drawable.ic_launcher_distance;
        holder.Image.setImageResource(data.get(position).getImage());
        holder.AddFriendicon.setImageResource(profileIcon);
        holder.activityIcon.setImageResource(activityIcon);
        holder.likeIcon.setImageResource(likeIcon);
        holder.timeIcon.setImageResource(timeIcon);
        holder.distanceIcon.setImageResource(distanceIcon);
    }

    @Override
    public int getItemCount() {
        // Return the number of social posts in the data list
        return data.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        // Define TextViews and ImageViews for each RecyclerView element
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

        /**
         * Constructor that takes in a view for the RecyclerView element.
         *
         * @param itemView the view representing a single RecyclerView element
         */
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
