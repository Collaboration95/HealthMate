/**

 This class represents the trend of an activity in the HealthMate app.
 It contains the name, number and unit of the activity, as well as an image
 that represents it.
 */
package com.example.healthmate;
public class Trend_Class {
    // Declare private instance variables
    private String activity_name;
    private String activity_number;
    private String activity_unit;
    private int image;
    /**
     * Constructor for the Trend_Class.
     * @param activity_name Name of the activity
     * @param activity_number Number of the activity
     * @param activity_unit Unit of the activity
     * @param image Image that represents the activity
     */
    public Trend_Class(String activity_name, String activity_number, String activity_unit, int image) {
        // Initialize instance variables
        this.activity_name = activity_name;
        this.activity_number = activity_number;
        this.activity_unit = activity_unit;
        this.image = image;
    }

    /**
     * Returns the name of the activity.
     * @return The name of the activity
     */
    public String getActivity_name() {
        return activity_name;
    }

    /**
     * Returns the number of the activity.
     * @return The number of the activity
     */
    public String getActivity_number() {
        return activity_number;
    }

    /**
     * Returns the unit of the activity.
     * @return The unit of the activity
     */
    public String getActivity_unit() {
        return activity_unit;
    }

    /**
     * Returns the image that represents the activity.
     * @return The image that represents the activity
     */
    public int getImage() {
        return image;
    }
}

