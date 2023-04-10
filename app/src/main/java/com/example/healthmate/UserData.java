package com.example.healthmate;

public class UserData {
    private static UserData instance = null;
    private String userName;
    private String sex;
    private int weight;
    private int height;
    private int calorie_intake_goal;
    private int workoutGoal;

    private UserData(String userName, String sex, int weight, int height, int calorie_intake_goal, int workoutGoal) {
        this.userName = userName;
        this.sex = sex;
        this.weight = weight;
        this.height = height;
        this.calorie_intake_goal = calorie_intake_goal;
        this.workoutGoal = workoutGoal;
    }

    public static UserData getInstance(String userName, String sex, int weight, int height, int calorie_intake_goal, int workoutGoal) {
        if (instance == null) {
            instance = new UserData(userName, sex, weight, height, calorie_intake_goal, workoutGoal);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public String getSex() {
        return sex;
    }

    public int getWeight() {
        return weight;
    }


    public int getHeight() {
        return height;
    }

    public int getCalorieIntakeGoal() {
        return calorie_intake_goal;
    }

    public int getWorkoutGoal() {
        return workoutGoal;
    }

    public void UpdateData(String userName, String sex, int weight, int height, int calorie_intake_goal, int workoutGoal) {
        this.userName=userName;
        this.sex=sex;
        this.weight=weight;
        this.height=height;
        this.calorie_intake_goal = calorie_intake_goal;
        this.workoutGoal = workoutGoal;
    }
    public void populateDefault() {
        this.userName="Guru";
        this.sex="Male";
        this.weight=weight;
        this.height=height;
        this.calorie_intake_goal = calorie_intake_goal;
        this.workoutGoal = workoutGoal;
    }



}