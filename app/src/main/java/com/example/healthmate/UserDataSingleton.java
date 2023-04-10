package com.example.healthmate;

import java.util.Comparator;

public class UserDataSingleton {

    private static UserDataSingleton instance = null;
    private UserData userData;

    private UserDataSingleton() {
        this.userData = new UserData();
    }

    public static UserDataSingleton getInstance() {
        if (instance == null) {
            instance = new UserDataSingleton();
        }
        return instance;
    }

    // Getter and Setter methods for UserData
    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    // Comparator for UserData objects
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

    @Override
    public String toString() {
        return userData.toString();
    }
}
