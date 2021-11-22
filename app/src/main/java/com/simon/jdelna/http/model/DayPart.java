package com.simon.jdelna.http.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class DayPart {
    @SerializedName("nazev")
    String title;
    @SerializedName("menu")
    List<Food> foods;
    @SerializedName("objednavky")
    Map<String, Order> orders;

    public String getTitle() {
        return title;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public Map<String, Order> getOrders() {
        return orders;
    }
}
