package com.example.healthmate;

import android.database.Observable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * The Social_PostModelHolder2 is a singleton class that stores and manages
 * social_postModel instances. It also notifies observers when changes occur.
 */
public class Social_PostModelHolder2 extends Observable {
    private static Social_PostModelHolder2 instance;
    private List<MyObserver> observers = new ArrayList<>();

    private ArrayList<Social_PostModel> data;

    private Social_PostModelHolder2() {
        data = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the Social_PostModelHolder2 class.
     *
     * @return The instance of Social_PostModelHolder2.
     */
    public static Social_PostModelHolder2 getInstance() {
        if (instance == null) {
            instance = new Social_PostModelHolder2();
        }
        return instance;
    }

    /**
     * Stores a social_postModel instance and notifies observers.
     *
     * @param post The social_postModel instance to be stored.
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
     * Returns the number of social_postModel instances stored.
     *
     * @return The number of social_postModel instances.
     */
    public int postCount() {
        return data.size();
    }

    /**
     * Returns the stored social_postModel instances.
     *
     * @return The ArrayList of social_postModel instances.
     */
    public ArrayList<Social_PostModel> getData() {
        return data;
    }

    /**
     * Returns a specific social_postModel instance based on its position.
     *
     * @param position The position of the social_postModel instance.
     * @return The social_postModel instance at the specified position.
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
