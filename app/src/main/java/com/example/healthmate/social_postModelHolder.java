package com.example.healthmate;

import android.database.Observable;

import java.util.ArrayList;
import java.util.List;


public class social_postModelHolder extends Observable {
    private static social_postModelHolder instance;
    private List<MyObserver> observers = new ArrayList<>();

    private ArrayList<social_postModel> data;

    private social_postModelHolder() {
        data = new ArrayList<>();
    }

    public static social_postModelHolder getInstance() {
        if (instance == null) {
            instance = new social_postModelHolder();
        }
        return instance;
    }

    public void storePost(social_postModel post) {
        data.add(post);
        notifyObservers();
    }
    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MyObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (MyObserver observer : observers) {
            observer.onMealHolderChange();
        }
    }

    public int postCount() {
        return data.size();
    }

    public ArrayList<social_postModel> getData() {
        return data;
    }

    public social_postModel getPost(int position) {
        return data.get(position);
    }

}
