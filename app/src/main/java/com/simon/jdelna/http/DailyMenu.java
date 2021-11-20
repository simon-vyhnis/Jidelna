package com.simon.jdelna.http;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyMenu {
    @SerializedName("datum")
    String date;
    @SerializedName("den")
    Day day;

    public String getDate() {
        return date;
    }

    public Day getDay() {
        return day;
    }

    public class Day{
        @SerializedName("castiDne")
        List<DayPart> dayParts;

    }

    public class DayPart{
        @SerializedName("nazev")
        String name;
        @SerializedName("menu")
        List<Food> foods;

        public List<Food> getFoods() {
            return foods;
        }
    }

    public class Food{
        @SerializedName("chody")
        List<Course> courses;
        @SerializedName("nazev")
        String name;

        public class Course{
            @SerializedName("nazev")
            String title;
            @SerializedName("jidlo")
            String content;

            public String getTitle() {
                return title;
            }

            public String getContent() {
                return content;
            }
        }

        public List<Course> getCourses() {
            return courses;
        }

        public String getName() {
            return name;
        }
    }

    public List<Food> getFoods(){
        if(day.dayParts.size()>=1) {
            return day.dayParts.get(0).getFoods();
        }
        return null;
    }

}
