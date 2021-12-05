package com.simon.jdelna.http.model;

import com.google.gson.annotations.SerializedName;

public class OrderRequest {
    @SerializedName("idUzivatele")
    private String userId;
    @SerializedName("idMenu")
    private String menuId;
    @SerializedName("den")
    private String date;
    @SerializedName("stav")
    private String state;
    @SerializedName("mnozstvi")
    private int count;
    private DayPart dayPart;

    public OrderRequest(String userId, String date, DayPart dayPart){
        this(userId, Integer.toString(dayPart.getSelectedFood().getId()), date, dayPart.isSelected(), dayPart);
    }

    private OrderRequest(String userId, String menuId, String date, boolean ordered, DayPart dayPart) {
        this.userId = userId;
        this.menuId = menuId;
        this.date = date;
        this.dayPart = dayPart;
        if(ordered){
            this.state = "Prihlaseno";
            this.count = 1;
        }else {
            this.state = "Odhlaseno";
            this.count = 0;
        }
    }

    public DayPart getDayPart() {
        return dayPart;
    }
}
