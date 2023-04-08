package com.example.healthmate;

import androidx.annotation.NonNull;

public class plus_AddMeal {
    private final String mealName;
    private final String protein;
    private final String fat;
    private final String carbs;
    private final String totalCalories;
    private final String time;

    public plus_AddMeal(String mealName,String protein,String fat,String carbs, String totalCalories, String time) {
        this.mealName = mealName;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.totalCalories = totalCalories;
        this.time = time;
    }

    public String getMealName() {
        return mealName;
    }

    public String getProtein() {
        return protein;
    }

    public String getFat() {
        return fat;
    }

    public String getCarbs() {
        return carbs;
    }

    public String getTotalCalories() {
        return totalCalories;
    }

    public String getTime() {
        return time;
    }
    @NonNull
    @Override
    public String toString() {
        return "Meal Name: " + mealName +
                ", Protein: " + protein +
                ", Fat: " + fat +
                ", Carbs: " + carbs +
                ", Total Calories: " + totalCalories +
                ", Time: " + time;
    }
}
