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

    public static void  populateDefault2() {

        String[] Name = {"Olivia Smith", "Lucas Johnson", "Sophie Garcia", "Isaac Rodriguez", "Ethan Hernandez", "Chloe Martinez", "Alexander Brown"};
        String[] activityName = {"Morning Run", "Yoga Class", "Cycling", "Swimming", "Evening Walk", "Weightlifting", "Hiking"};
        String[] Likes = {"20", "6", "15", "9", "3", "27", "12"};
        String[] Distance = {"3.1km", "2.7km", "7.8km", "4.5km", "1.8km", "6.2km", "5.3km"};
        String[] Time = {"27m", "31m", "45m", "52m", "18m", "38m", "1h 5m"};

        int Image = R.drawable.ic_launcher_background;
        int profileIcon = R.drawable.ic_launcher_person;
        int activityIcon = R.drawable.ic_launcher_rightsymbol;
        int likeIcon = R.drawable.ic_launcher_addfriend;
        int timeIcon = R.drawable.ic_launcher_timer;
        int distanceIcon = R.drawable.ic_launcher_distance;
        for (int i = 0; i < Name.length; i++) {
            social_postModelHolder2.getInstance().storePost(new social_postModel(Name[i], activityName[i], Distance[i], Time[i], Likes[i], profileIcon, activityIcon, likeIcon, timeIcon, distanceIcon, Image));
        }
    }


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

    public static void  populateDefault() {
        String[] Name = {"Maggie Smigth", "John Fugyoshi ", "Sarah Johnson", "Michael Brown", "Rachel Lee", "David Chen", "Emily Taylor"};
        String[] activityName = {"Evening Jog", "Morning Jog", "Hiking", "Biking", "Swimming", "Yoga", "Pilates"};
        String[] Likes = {"12", "10", "8", "5", "17", "133", "9"};
        String[] Distance = {"1.23km", "2.3km", "5.1km", "2.27km", "3.8km", "4.2km", "6.5km"};
        String[] Time = {"10m", "12m", "30m", "25m", "50m", "42m", "552m"};

        int Image = R.drawable.ic_launcher_background;
        int profileIcon = R.drawable.ic_launcher_person;
        int activityIcon = R.drawable.ic_launcher_rightsymbol;
        int likeIcon = R.drawable.ic_launcher_addfriend;
        int timeIcon = R.drawable.ic_launcher_timer;
        int distanceIcon = R.drawable.ic_launcher_distance;
        for (int i = 0; i < Name.length; i++) {
            social_postModelHolder.getInstance().storePost(new social_postModel(Name[i], activityName[i], Distance[i], Time[i], Likes[i], profileIcon, activityIcon, likeIcon, timeIcon, distanceIcon, Image));
        }
    }
}
