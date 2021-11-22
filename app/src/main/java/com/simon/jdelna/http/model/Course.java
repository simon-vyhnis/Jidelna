package com.simon.jdelna.http.model;

import com.google.gson.annotations.SerializedName;

public class Course {
    @SerializedName("nazev")
    private String title;
    @SerializedName("jidlo")
    private String content;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
