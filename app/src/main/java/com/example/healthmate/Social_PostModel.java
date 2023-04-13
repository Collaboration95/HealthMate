package com.example.healthmate;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Social_PostModel class represents a single post object in the social feed.
 */
public class Social_PostModel {
    String Name;
    String activityName;
    String Distance;
    String Time;
    String likes;
    int image;
    Bitmap customImage;

    public Bitmap getCustomImage() {
        return customImage;
    }

    /**
     * Populates the Social_PostModelHolder2 with default data for testing.
     */
    public static void populateDefault2() {
        String[] Name = {"Olivia Smith", "Lucas Johnson", "Sophie Garcia", "Isaac Rodriguez", "Ethan Hernandez", "Chloe Martinez", "Alexander Brown"};
        String[] activityName = {"Morning Run", "Yoga Class", "Cycling", "Swimming", "Evening Walk", "Weightlifting", "Hiking"};
        String[] Likes = {"20", "6", "15", "9", "3", "27", "12"};
        String[] Distance = {"3.1km", "2.7km", "7.8km", "4.5km", "1.8km", "6.2km", "5.3km"};
        String[] Time = {"27m", "31m", "45m", "52m", "18m", "38m", "1h 5m"};
        int[] images = {R.drawable.stock3, R.drawable.stock1, R.drawable.stock2, R.drawable.stock6, R.drawable.stock5, R.drawable.stock4, R.drawable.stock7};
        for (int i = 0; i < Name.length; i++) {
            Social_PostModelHolder2.getInstance().storePost(new Social_PostModel(Name[i], activityName[i], Distance[i], Time[i], Likes[i],images[i]));
        }
    }

    /**
     * Constructs a Social_PostModel with the provided data.
     *
     * @param name         The name of the user.
     * @param activityName The name of the activity.
     * @param distance     The distance of the activity.
     * @param time         The duration of the activity.
     * @param likes        The number of likes for the post.
     */
    public Social_PostModel(String name, String activityName, String distance, String time, String likes,int image) {
        Log.d("Normal gets evoked","Great Success");
        this.Name = name;
        this.activityName = activityName;
        this.Distance = distance;
        this.Time = time;
        this.likes = likes;
        this.image   =image;
        this.customImage = null;

    }

    public Social_PostModel(String name, String activityName, String distance, String time, String likes,Bitmap customeImage) {
        Log.d("BitmapConstructor gets evoked","Great Success");
        this.Name = name;
        this.activityName = activityName;
        this.Distance = distance;
        this.Time = time;
        this.likes = likes;
        this.image = 0;
        this.customImage = customeImage;
    }


    public String getName() {
        return Name;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getDistance() {
        return Distance;
    }

    public String getTime() {
        return Time;
    }

    public int getImage() {
        return image;
    }

    public String getLikes() {
        return likes;
    }

    /**
     * Populates the Social_PostModelHolder with default data for testing.
     */
    public static void populateDefault() {
        String[] Name = {"Maggie Smigth", "John Fugyoshi ", "Sarah Johnson", "Michael Brown", "Rachel Lee", "David Chen", "Emily Taylor"};
        String[] activityName = {"Evening Jog", "Morning Jog", "Hiking", "Biking", "Swimming", "Yoga", "Pilates"};
        String[] Likes = {"12", "10", "8", "5", "17", "133", "9"};
        String[] Distance = {"1.23km", "2.3km", "5.1km", "2.27km", "3.8km", "4.2km", "6.5km"};
        String[] Time = {"10m", "12m", "30m", "25m", "50m", "42m", "552m"};
        int[] images = {R.drawable.stock1, R.drawable.stock2, R.drawable.stock3, R.drawable.stock4, R.drawable.stock5, R.drawable.stock6, R.drawable.stock7};

        for (int i = 0; i < Name.length; i++) {
            Social_PostModelHolder.getInstance().storePost(new Social_PostModel(Name[i], activityName[i], Distance[i], Time[i], Likes[i],images[i]));
        }
    }

    @Override
    public String toString() {
        return "Social_PostModel{" +
                "Name='" + Name + '\'' +
                ", activityName='" + activityName + '\'' +
                ", Distance='" + Distance + '\'' +
                ", Time='" + Time + '\'' +
                ", likes='" + likes + '\'' +
                ", image=" + image +
                ", customImage=" + customImage +
                '}';
    }

}
