package com.example.healthmate;

import androidx.annotation.NonNull;

/**
 * This class represents a single meal entry, containing information about
 * the meal name, protein, fat, carbs, total calories, and the time the meal was consumed.
 */
public class Plus_AddMeal {
    private final String mealName;
    private final String protein;
    private final String fat;
    private final String carbs;
    private final String totalCalories;
    private final String time;

    /**
     * Constructs a new meal object.
     *
     * @param mealName      The name of the meal.
     * @param protein       The protein content of the meal (in grams).
     * @param fat           The fat content of the meal (in grams).
     * @param carbs         The carbohydrate content of the meal (in grams).
     * @param totalCalories The total calorie content of the meal.
     * @param time          The time the meal was consumed.
     */
    public Plus_AddMeal(String mealName, String protein, String fat, String carbs, String totalCalories, String time) {
        this.mealName = mealName;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.totalCalories = totalCalories;
        this.time = time;
    }

    /**
     * @return The name of the meal.
     */
    public String getMealName() {
        return mealName;
    }

    /**
     * @return The protein content of the meal (in grams).
     */
    public String getProtein() {
        return protein;
    }

    /**
     * @return The fat content of the meal (in grams).
     */
    public String getFat() {
        return fat;
    }

    /**
     * @return The carbohydrate content of the meal (in grams).
     */
    public String getCarbs() {
        return carbs;
    }

    /**
     * @return The total calorie content of the meal.
     */
    public String getTotalCalories() {
        return totalCalories;
    }

    /**
     * @return The time the meal was consumed.
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns a string representation of the meal object.
     *
     * @return A string containing information about the meal.
     */
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
