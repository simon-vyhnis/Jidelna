package com.simon.jdelna.http.model;

import com.google.gson.annotations.SerializedName;
import com.simon.jdelna.http.DailyMenu;

import java.util.List;

public class Food {
    @SerializedName("chody")
    List<Course> courses;
    @SerializedName("nazev")
    String name;
    int id;

    public List<Course> getCourses() {
        return courses;
    }

    public String getName() {
        return name;
    }
    public String getContent(){
        StringBuilder builder = new StringBuilder();
        for (Course course : courses) {
            builder.append(course.getTitle()).append(": ").append(course.getContent()).append(", ");
        }
        builder.deleteCharAt(builder.length()-2);
        return builder.toString();
    }

    public int getId() {
        return id;
    }
}
