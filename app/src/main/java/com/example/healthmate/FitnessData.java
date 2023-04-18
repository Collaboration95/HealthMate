package com.example.healthmate;

public class FitnessData implements FitnessDataInterface {
    private long id;
    private int steps;
    private float distance;
    private float calories;
    private long timestamp;

    public FitnessData(int steps, float distance, float calories, long timestamp) {
        this.steps = steps;
        this.distance = distance;
        this.calories = calories;
        this.timestamp = timestamp;
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSteps() {
        return steps;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

/**
 * To extend the functionality of FitnessData, you can create a new class that implements the FitnessDataInterface
 * without modifying the existing FitnessData class.
 * The new class can have additional functionality specific to the new requirements.
 *
 * By using the Open/Closed Principle, you can extend the functionality of the FitnessData class without modifying the original class,
 * keeping it closed for modification while allowing new features to be added through extension.
 */
