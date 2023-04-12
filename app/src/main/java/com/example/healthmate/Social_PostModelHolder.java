package com.example.healthmate;

import android.database.Observable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * The Social_PostModelHolder is a singleton class that stores and manages
 * Social_PostModel instances. It also notifies observers when changes occur.
 */
public class Social_PostModelHolder extends Observable {
    private static Social_PostModelHolder instance;
    private final List<MyObserver> observers = new ArrayList<>();

    private final ArrayList<Social_PostModel> data;

    private Social_PostModelHolder() {
        data = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the Social_PostModelHolder class.
     *
     * @return The instance of Social_PostModelHolder.
     */
    public static Social_PostModelHolder getInstance() {
        if (instance == null) {
            instance = new Social_PostModelHolder();
        }
        return instance;
    }

    /**
     * Stores a Social_PostModel instance and notifies observers.
     *
     * @param post The Social_PostModel instance to be stored.
     */
    public void storePost(Social_PostModel post) {
        data.add(post);
        notifyObservers();
    }

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer The observer to be added.
     */
    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer The observer to be removed.
     */
    public void removeObserver(MyObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers when a change occurs.
     */
    public void notifyObservers() {
        for (MyObserver observer : observers) {
            observer.OnChange();
        }
    }

    /**
     * Returns the number of Social_PostModel instances stored.
     *
     * @return The number of Social_PostModel instances.
     */
    public int postCount() {
        return data.size();
    }

    /**
     * Returns the stored Social_PostModel instances.
     *
     * @return The ArrayList of Social_PostModel instances.
     */
    public ArrayList<Social_PostModel> getData() {
        return data;
    }

    /**
     * Returns a specific Social_PostModel instance based on its position.
     *
     * @param position The position of the Social_PostModel instance.
     * @return The Social_PostModel instance at the specified position.
     */
    public Social_PostModel getPost(int position) {
        return data.get(position);
    }
    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("social_postModelHolder: {");
        for (Social_PostModel post : data) {
            sb.append("\n\t").append(post.toString());
        }
        sb.append("\n}");
        return sb.toString();
    }
}
