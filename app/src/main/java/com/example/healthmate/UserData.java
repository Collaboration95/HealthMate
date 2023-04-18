package com.example.healthmate;


public class UserData {
    private String userName;
    private int sex;
    private int weight;
    private int height;
    private int calorie_intake_goal;
    private int workoutGoal;
    private boolean isDefault=true;

    /**
     * Default constructor that populates UserData with default values.
     */
    public UserData() {
        populateDefault();
    }

    /**
     * Getter for the user's name.
     *
     * @return the user's name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter for the user's sex.
     *
     * @return the user's sex
     */
    public int getSex() {
        return sex;
    }

    /**
     * Getter for the user's weight.
     *
     * @return the user's weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Getter for the user's height.
     *
     * @return the user's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter for the user's calorie intake goal.
     *
     * @return the user's calorie intake goal
     */
    public int getCalorieIntakeGoal() {
        return calorie_intake_goal;
    }

    /**
     * Getter for the user's workout goal.
     *
     * @return the user's workout goal
     */
    public int getWorkoutGoal() {
        return workoutGoal;
    }

    /**
     * Checks if the UserData object contains default values.
     *
     * @return true if the UserData object has default values, otherwise false
     */
    public boolean isDefault(){
        return this.isDefault;
    }

    /**
     * Updates the UserData object with new values.
     *
     * @param userName          the user's name
     * @param sex               the user's sex
     * @param weight            the user's weight
     * @param height            the user's height
     * @param calorie_intake_goal the user's calorie intake goal
     * @param workoutGoal       the user's workout goal
     */
    public void updateData(String userName, int sex, int weight, int height, int calorie_intake_goal, int workoutGoal) {
        this.userName=userName;
        this.sex=sex;
        this.weight=weight;
        this.height=height;
        this.calorie_intake_goal = calorie_intake_goal;
        this.workoutGoal = workoutGoal;
        this.isDefault=false;
    }

    /**
     * Populates the UserData object with default values.
     */
    public void populateDefault() {
        this.userName="Guru";
        this.sex=1;
        this.weight=89;
        this.height=181;
        this.calorie_intake_goal = 2400;
        this.workoutGoal = 2900;
    }

    /**
     * Returns a string representation of the UserData object.
     *
     * @return a string representation of the UserData object
     */
    @Override
    public String toString() {
        String[] Spinner_Array ={"","Male","Female","Non-binary","Yes-Please"};
        return "User Data: \n" +
                "User Name: " + userName + "\n" +
                "Sex :"+ Spinner_Array[sex]+"\n"+
                "Weight: " + weight + "\n" +
                "Height: " + height + "\n" +
                "Calorie Intake Goal: " + calorie_intake_goal + "\n" +
                "Workout Goal: " + workoutGoal + "\n" +
                "Is Default: " + isDefault + "\n";
    }
}
