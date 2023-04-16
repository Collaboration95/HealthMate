package com.example.healthmate;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;

/**
 * This class represents the MealAdapter, which is a custom RecyclerView.Adapter
 * to display meal data in a RecyclerView.
 */
public class MealAdapter extends RecyclerView.Adapter<MealAdapter.myViewHolder> {
    Context context;
    ArrayList<Plus_AddMeal> data;

    public MealAdapter(Context context, ArrayList<Plus_AddMeal> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.meal_layout, parent, false);
        return new MealAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.totalCalorie.setText(data.get(position).getTotalCalories());
        holder.mealTitle.setText(data.get(position).getMealName());

        // Carbohydrates provide 4 calories per gram, protein provides 4 calories per gram, and fat provides 9 calories per gram
        int tempCarbs = Integer.parseInt(data.get(position).getCarbs()) * 4;
        int tempFat = Integer.parseInt(data.get(position).getFat()) * 9;
        int tempProtein = Integer.parseInt(data.get(position).getProtein()) * 4;

        // Update progress indicators
        int totalCalories = tempCarbs + tempFat + tempProtein;
        holder.proteinProgress.setProgress((tempProtein * 100) / totalCalories, true);
        holder.fatProgress.setProgress((tempFat * 100) / totalCalories, true);
        holder.carbProgress.setProgress((tempCarbs * 100) / totalCalories, true);
        holder.materialCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent fillMeal = new Intent(context, NewMeal.class);
                fillMeal.putExtra("pos", holder.getAdapterPosition() + " ");
                fillMeal.putExtra("activity", "meal");
                context.startActivity(fillMeal);
                Log.d("Material Card View", NewMealHolder.getInstance().getMeal(holder.getAdapterPosition()) + " ");
                return true; // indicate that the long click event was consumed
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<Plus_AddMeal> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class to store and manage the views for each data item.
     */
    public static class myViewHolder extends RecyclerView.ViewHolder {
        public View materialCardView;
        LinearProgressIndicator proteinProgress;
        LinearProgressIndicator fatProgress;
        LinearProgressIndicator carbProgress;
        TextView totalCalorie;
        TextView mealTitle;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            totalCalorie = itemView.findViewById(R.id.totalCalorie);
            mealTitle = itemView.findViewById(R.id.mealTitle);
            proteinProgress = itemView.findViewById(R.id.proteinProgress);
            fatProgress = itemView.findViewById(R.id.fatProgress);
            carbProgress = itemView.findViewById(R.id.carbProgress);
            materialCardView= itemView.findViewById(R.id.MealLayout);
        }
    }
}
