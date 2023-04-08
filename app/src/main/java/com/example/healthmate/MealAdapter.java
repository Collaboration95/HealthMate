package com.example.healthmate;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.myViewHolder> {
    Context context;
    ArrayList<plus_AddMeal> data ;

    public MealAdapter(Context context, ArrayList<plus_AddMeal> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.meallayout,parent ,false);
        return new MealAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.totalCalorie.setText(data.get(position).getTotalCalories());
        holder.mealtitle.setText(data.get(position).getMealName());
        Log.d("Great Success",data.get(position).toString());
//        Carbohydrates provide 4 calories per gram, protein provides 4 calories per gram, and fat provides 9 calories per gram
//        Carbs -> 4 * gram , Fat -> 9*gram , protien -> 4*gram
        int tempcarbs = Integer.parseInt(data.get(position).getCarbs())*4;
        int tempfat = Integer.parseInt(data.get(position).getFat())*9;
        int tempprotein = Integer.parseInt(data.get(position).getProtein())*4;
        holder.proteinProgress.setProgress((tempprotein*100)/(tempcarbs+tempfat+tempprotein),true);
        holder.fatProgress.setProgress((tempfat*100)/(tempcarbs+tempfat+tempprotein),true);
        holder.CarbProgress.setProgress((tempcarbs*100)/(tempcarbs+tempfat+tempprotein),true);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<plus_AddMeal> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    public  static class myViewHolder extends  RecyclerView.ViewHolder {

        LinearProgressIndicator proteinProgress ;
        LinearProgressIndicator fatProgress ;
        LinearProgressIndicator CarbProgress;
        TextView totalCalorie;
        TextView mealtitle;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            totalCalorie = itemView.findViewById(R.id.totalCalorie);
            mealtitle = itemView.findViewById(R.id.mealtitle);
            proteinProgress = itemView.findViewById(R.id.proteinProgress);
            fatProgress =  itemView.findViewById(R.id.fatProgress);
            CarbProgress =  itemView.findViewById(R.id.CarbProgress);

        }

    }
}