/**
 * HealthMate
 *
 * NewMealHolder Class
 *
 * This class is a singleton that manages and stores meal data for the HealthMate application.
 * It extends the Observable class, enabling observer classes to be notified when changes occur
 * in the meal data.
 */
package com.example.healthmate;

import android.database.Observable;

import java.util.ArrayList;
import java.util.List;

public class NewMealHolder extends Observable {
    private static NewMealHolder instance;
    private final List<MyObserver> observers = new ArrayList<>();

    private final ArrayList<Plus_AddMeal> data;

    // Private constructor to enforce singleton pattern
    private NewMealHolder() {
        data = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the NewMealHolder class.
     *
     * @return the singleton instance
     */
    public static NewMealHolder getInstance() {
        if (instance == null) {
            instance = new NewMealHolder();
        }
        return instance;
    }

    /**
     * Adds a meal to the meal data and notifies observers of the change.
     *
     * @param meal the meal to be added
     */
    public void storeMeal(Plus_AddMeal meal) {
        data.add(meal);
        notifyObservers();
    }
    public void deleteData() {
        data.clear();
        notifyObservers();
    }

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer the observer to be added
     */
    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer the observer to be removed
     */
    public void removeObserver(MyObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers of changes in the meal data.
     */
    public void notifyObservers() {
        for (MyObserver observer : observers) {
            observer.OnChange();
        }
    }

    /**
     * Returns the number of meals in the meal data.
     *
     * @return the number of meals
     */
    public int getMealCount() {
        return data.size();
    }

    /**
     * Returns the meal data.
     *
     * @return the meal data
     */
    public ArrayList<Plus_AddMeal> getData() {
        return data;
    }

    /**
     * Returns the meal at the specified position.
     *
     * @param position the position of the meal
     * @return the meal
     */
    public Plus_AddMeal getMeal(int position) {
        return data.get(position);
    }

    /**
     * Returns the total calories of all meals in the meal data.
     *
     * @return the total calories
     */
    public int TotalCalories() {
        int totalCalories = 0;
        for (Plus_AddMeal meal : data) {
            totalCalories += Integer.parseInt(meal.getTotalCalories());
        }
        return totalCalories;
    }

    /**
     * Returns the total protein of all meals in the meal data.
     *
     * @return the total protein
     */
    public int TotalProtein() {
        int totalProtein = 0;
        for (Plus_AddMeal meal : data) {
            totalProtein += Integer.parseInt(meal.getProtein());
        }
        return totalProtein;
    }

    /**
     * Returns the total fat of all meals in the meal data.
     *
     * @return the total fat
     */
    public int TotalFat() {
        int totalFat = 0;
        for (Plus_AddMeal meal : data) {
            totalFat += Integer.parseInt(meal.getFat());
        }
        return totalFat;
    }

    /**
     * Returns the total carbohydrates of all meals in the meal
     * * @return the total carbohydrates
     */

    public int TotalCarbs() {
        int totalCarbs = 0;
        for (Plus_AddMeal meal : data) {
            totalCarbs += Integer.parseInt(meal.getCarbs());
        }
        return totalCarbs;
    }
}