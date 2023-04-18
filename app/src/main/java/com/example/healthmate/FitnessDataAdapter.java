package com.example.healthmate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * This class is an adapter for displaying the fitness data in a RecyclerView within
 * the HealthMate application. It binds the data from FitnessData objects to the
 * corresponding views in the RecyclerView items.
 */
public class FitnessDataAdapter extends RecyclerView.Adapter<FitnessDataAdapter.ViewHolder> {

    // List of fitness data items to display in the RecyclerView
    private final List<FitnessData> fitnessDataList;

    // Constructor for the FitnessDataAdapter
    // Accepts a list of FitnessData objects to be displayed
    public FitnessDataAdapter(List<FitnessData> fitnessDataList) {
        this.fitnessDataList = fitnessDataList;
    }

    // Called when a ViewHolder is created for the RecyclerView
    // Inflates the layout for each item in the RecyclerView and returns a new ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fitness_data_list_item, parent, false);
        return new ViewHolder(view);
    }

    // Called to bind the data from the FitnessData object to the ViewHolder
    // Sets the text for each TextView in the ViewHolder based on the FitnessData object's properties
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FitnessData fitnessData = fitnessDataList.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateString = sdf.format(fitnessData.getTimestamp());
        String stepsString = fitnessData.getSteps() + " steps";
        String distanceString = String.format(Locale.getDefault(), "%.2f km", fitnessData.getDistance());
        String caloriesString = String.format(Locale.getDefault(), "%.0f kcal", fitnessData.getCalories());

        holder.dateTextView.setText(dateString);
        holder.stepsTextView.setText(stepsString);
        holder.distanceTextView.setText(distanceString);
        holder.caloriesTextView.setText(caloriesString);
    }
    // Returns the total number of items in the RecyclerView
    @Override
    public int getItemCount() {
        return fitnessDataList.size();
    }

    // ViewHolder class for RecyclerView items
    // Holds references to the TextViews in the layout for each item
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView stepsTextView;
        TextView distanceTextView;
        TextView caloriesTextView;

        // Constructor for the ViewHolder
        // Initializes the TextView references using the itemView's findViewById method
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date_text_view);
            stepsTextView = itemView.findViewById(R.id.steps_text_view);
            distanceTextView = itemView.findViewById(R.id.distance_text_view);
            caloriesTextView = itemView.findViewById(R.id.calories_text_view);
        }
    }
}