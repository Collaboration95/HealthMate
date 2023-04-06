package com.example.healthmate;

public class social_postModel {
    String Name;
    String activityName;
    String Distance;
    String Time;
    String likes;
    int Image;
    int AddFriendicon;
    int activityIcon;
    int likeIcon;
    int timeIcon;
    int distanceIcon;


    public int getImage() {
        return Image;
    }

    public social_postModel(String name, String activityName, String distance, String time, String likes, int addFriendicon, int activityIcon, int likeIcon, int timeIcon, int distanceIcon, int Image) {
        this.Name = name;
        this.activityName = activityName;
        this.Distance = distance;
        this.Time = time;
        this.likes = likes;
        this.AddFriendicon = addFriendicon;
        this.activityIcon = activityIcon;
        this.likeIcon = likeIcon;
        this.timeIcon = timeIcon;
        this.distanceIcon = distanceIcon;
        this.Image = Image;
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

    public String getLikes() {
        return likes;
    }

    public int getAddFriendicon() {
        return AddFriendicon;
    }

    public int getActivityIcon() {
        return activityIcon;
    }

    public int getLikeIcon() {
        return likeIcon;
    }

    public int getTimeIcon() {
        return timeIcon;
    }

    public int getDistanceIcon() {
        return distanceIcon;
    }
}
