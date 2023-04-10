package com.example.healthmate;

/**
 * AppManager is a singleton class that manages instances of other classes
 * such as GoogleFitManager. It ensures that there is only one instance
 * of each managed class throughout the application.
 */
public class AppManager {
    private static AppManager instance;
    private GoogleFitManager googleFitManager;

    /**
     * Private constructor to prevent creating more than one instance
     * of AppManager.
     */
    private AppManager() {
    }

    /**
     * Returns the instance of AppManager, creating it if it doesn't exist.
     *
     * @return The instance of AppManager.
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * Returns the instance of GoogleFitManager.
     *
     * @return The instance of GoogleFitManager.
     */
    public GoogleFitManager getGoogleFitManager() {
        return googleFitManager;
    }

    /**
     * Sets the instance of GoogleFitManager.
     *
     * @param googleFitManager The GoogleFitManager instance to be managed.
     */
    public void setGoogleFitManager(GoogleFitManager googleFitManager) {
        this.googleFitManager = googleFitManager;
    }
}
