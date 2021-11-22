package com.simon.jdelna.http.model;

import com.google.gson.annotations.SerializedName;

public class Order {
    @SerializedName("idMenu")
    public int foodId;
    @SerializedName("idUzivatele")
    String userId;

    public int getFoodId() {
        return foodId;
    }

    public String getUserId() {
        return userId;
    }
}
