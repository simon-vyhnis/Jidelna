package com.simon.jdelna.http.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DayPart implements Serializable {
    @SerializedName("nazev")
    String title;
    @SerializedName("menu")
    List<Food> foods;
    @SerializedName("objednavky")
    Map<String, Order> orders;
    private Food selectedFood;
    private boolean ordered;
    private boolean selected;

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

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
