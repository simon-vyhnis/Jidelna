package com.simon.jdelna.http.model;

import com.google.gson.annotations.SerializedName;

public class DayWrap {
    @SerializedName("den")
    Day day;
    @SerializedName("datum")
    String date;

    public Day getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }
}
