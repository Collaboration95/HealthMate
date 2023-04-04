package com.example.healthmate;

public class trend_class{
    private String activity_name;
    private String activity_number;
    private String activity_unit;
    private int image;

    public trend_class(String activity_name, String activity_number, String activity_unit, int image) {
        this.activity_name = activity_name;
        this.activity_number = activity_number;
        this.activity_unit = activity_unit;
        this.image = image;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public String getActivity_number() {
        return activity_number;
    }

    public String getActivity_unit() {
        return activity_unit;
    }

    public int getImage() {
        return image;
    }
}