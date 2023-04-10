package com.example.healthmate;

public class UserDataSingleton {
    private static UserDataSingleton instance = null;
    private UserData userData;

    private UserDataSingleton() {
        userData = new UserData();
    }

    public static UserDataSingleton getInstance() {
        if (instance == null) {
            instance = new UserDataSingleton();
        }
        return instance;
    }

    public UserData getUserData() {
        return userData;
    }
}
