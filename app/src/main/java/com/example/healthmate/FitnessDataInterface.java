package com.example.healthmate;

/**
represents the methods for the FitnessData class
 **/
public interface FitnessDataInterface {
    long getId();
    void setId(long id);
    int getSteps();
    float getDistance();
    void setDistance(float distance);
    float getCalories();
    void setCalories(float calories);
    long getTimestamp();
}
