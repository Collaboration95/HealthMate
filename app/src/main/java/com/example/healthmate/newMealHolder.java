package com.example.healthmate;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class newMealHolder {
    ArrayList<plus_AddMeal> data = new ArrayList<>();

    public newMealHolder() {

    }

    public void storeMeal(plus_AddMeal meal){
        data.add(meal);
    }
    public int getMealCount(){
        return data.size();
    }
    public plus_AddMeal getMeal(int position){
        return data.get(position);
    }

}
