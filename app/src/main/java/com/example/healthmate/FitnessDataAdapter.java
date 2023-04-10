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

public class FitnessDataAdapter extends RecyclerView.Adapter<FitnessDataAdapter.ViewHolder> {

    private final List<FitnessData> fitnessDataList;

    public FitnessDataAdapter(List<FitnessData> fitnessDataList) {
        this.fitnessDataList = fitnessDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fitness_data_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FitnessData fitnessData = fitnessDataList.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateString = sdf.format(fitnessData.getTimestamp());
        String stepsString = fitnessData.getSteps() + " steps";
        String distanceString = String.format(Locale.getDefault(), "%.1f km", fitnessData.getDistance() / 1000);
        String caloriesString = String.format(Locale.getDefault(), "%.0f kcal", fitnessData.getCalories());

        holder.dateTextView.setText(dateString);
        holder.stepsTextView.setText(stepsString);
        holder.distanceTextView.setText(distanceString);
        holder.caloriesTextView.setText(caloriesString);
    }

    @Override
    public int getItemCount() {
        return fitnessDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView stepsTextView;
        TextView distanceTextView;
        TextView caloriesTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date_text_view);
            stepsTextView = itemView.findViewById(R.id.steps_text_view);
            distanceTextView = itemView.findViewById(R.id.distance_text_view);
            caloriesTextView = itemView.findViewById(R.id.calories_text_view);
        }
    }
}
