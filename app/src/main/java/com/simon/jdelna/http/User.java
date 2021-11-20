package com.simon.jdelna.http;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("jmeno")
    private String name;
    private int userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
