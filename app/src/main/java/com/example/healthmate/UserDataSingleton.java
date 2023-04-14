package com.example.healthmate;

import java.util.Comparator;


/**
 * UserDataSingleton is a singleton class that holds a single instance of UserData,
 * which represents the user's data such as name, sex, weight, height, calorie intake goal,
 * and workout goal. The singleton pattern ensures that there is only one instance of the
 * user's data throughout the app.
 */
public class UserDataSingleton {

    // The single instance of UserDataSingleton
    private static UserDataSingleton instance = null;
    private UserData userData;

    /**
     * Private constructor to prevent creating multiple instances of this class.
     */
    private UserDataSingleton() {
        this.userData = new UserData();
    }

    /**
     * Returns the single instance of UserDataSingleton.
     * If there is no instance yet, create a new one.
     *
     * @return the single instance of UserDataSingleton
     */
    public static UserDataSingleton getInstance() {
        if (instance == null) {
            instance = new UserDataSingleton();
        }
        return instance;
    }

    /**
     * Returns the UserData object associated with this singleton.
     *
     * @return the UserData object
     */
    public UserData getUserData() {
        return userData;
    }

    /**
     * Sets the UserData object for this singleton.
     *
     * @param userData the new UserData object
     */
    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    /**
     * A comparator for UserData objects.
     * Compares each field of the UserData objects to determine if they are equal.
     */
    public Comparator<UserData> userDataComparator = new Comparator<UserData>() {
        @Override
        public int compare(UserData userData1, UserData userData2) {
            if (userData1 == userData2) {
                return 0;
            }
            if (userData1 == null) {
                return -1;
            }
            if (userData2 == null) {
                return 1;
            }

            // Compare each field of the UserData objects
            if (userData1.getUserName().equals(userData2.getUserName())
                    && userData1.getSex() == userData2.getSex()
                    && userData1.getWeight() == userData2.getWeight()
                    && userData1.getHeight() == userData2.getHeight()
                    && userData1.getCalorieIntakeGoal() == userData2.getCalorieIntakeGoal()
                    && userData1.getWorkoutGoal() == userData2.getWorkoutGoal()) {
                return 0; // Objects are equal
            } else {
                return -1; // Objects are not equal
            }
        }
    };
    /**
     * Returns a string representation of the UserData object associated with this singleton.
     *
     * @return a string representation of the UserData object
     */
    @Override
    public String toString() {
        return userData.toString();
    }
}

