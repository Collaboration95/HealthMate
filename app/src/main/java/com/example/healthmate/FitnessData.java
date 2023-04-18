package com.example.healthmate;

/**
 * The FitnessData class represents a single fitness data entry.
 * It implements the FitnessDataInterface to ensure compatibility with other classes
 * and to facilitate extension following the Open/Closed Principle.
 */
public class FitnessData implements FitnessDataInterface {
    // Private attributes
    private long id;
    private int steps;
    private float distance;
    private float calories;
    private long timestamp;

    /**
     * Constructor for the FitnessData class.
     * @param steps     The number of steps for this data entry.
     * @param distance  The distance covered in this data entry.
     * @param calories  The calories burned in this data entry.
     * @param timestamp The timestamp of the data entry.
     */
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

