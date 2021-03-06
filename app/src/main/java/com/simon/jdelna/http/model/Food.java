package com.simon.jdelna.http.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Food implements Serializable {
    @SerializedName("chody")
    List<Course> courses;
    @SerializedName("nazev")
    String name;
    int id;
    private String content;
    private boolean isOrdered = false;

    public List<Course> getCourses() {
        return courses;
    }

    public String getName() {
        return name;
    }
    public String getContent(){
        if(content == null) {
            StringBuilder builder = new StringBuilder();
            for (Course course : courses) {
                builder.append(course.getTitle()).append(": ").append(course.getContent()).append(", ");
            }
            builder.deleteCharAt(builder.length() - 2);
            content =  builder.toString();
        }
        return content;
    }

    public int getId() {
        return id;
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }
}
