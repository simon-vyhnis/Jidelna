package com.simon.jdelna.http.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order implements Serializable {
    @SerializedName("idMenu")
    private int foodId;
    @SerializedName("idUzivatele")
    private String userId;
    @SerializedName("stav")
    private String state;

    public int getFoodId() {
        return foodId;
    }

    public String getUserId() {
        return userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
