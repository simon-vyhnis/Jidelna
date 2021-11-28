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
    private Food selectedFood;
    private Boolean ordered = null;

    public String getTitle() {
        return title;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public Map<String, Order> getOrders() {
        return orders;
    }

    public void selectFood(Food food){
        selectedFood = food;
    }

    public boolean isFoodSelected(Food food){
        return food == this.selectedFood;
    }

    public Food getSelectedFood() {
        return selectedFood;
    }

    public Boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }
}
