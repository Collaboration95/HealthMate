package com.example.healthmate;

public class FitnessData {
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

    public void setSteps(int steps) {
        this.steps = steps;
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

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
