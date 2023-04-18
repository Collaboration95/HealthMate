package com.example.healthmate;

/**
 * Represents the methods for the FitnessData class.
 * This interface provides a contract for the FitnessData class or any class that
 * implements this interface, ensuring that the required methods are implemented.
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
