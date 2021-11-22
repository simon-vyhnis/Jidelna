package com.simon.jdelna.http.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Day {
    @SerializedName("castiDne")
    List<DayPart> dayParts;

    public List<DayPart> getDayParts() {
        return dayParts;
    }
}
