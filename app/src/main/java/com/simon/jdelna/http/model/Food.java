package com.simon.jdelna.http.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Food {
    @SerializedName("chody")
    List<Course> courses;
    @SerializedName("nazev")
    String name;
    int id;
    private String content;

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
}
