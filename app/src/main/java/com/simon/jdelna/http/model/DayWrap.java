package com.simon.jdelna.http.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DayWrap {
    @SerializedName("den")
    private Day day;
    @SerializedName("datum")
    private String date;

    public Day getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    public String getFormattedDate(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate formattedDate = LocalDate.parse(date);
            String formattedDateStr = formattedDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
            return formattedDateStr.substring(0, 1).toUpperCase() + formattedDateStr.substring(1);
        }
        return date;
    }
    
    public void formatData(String userId){
        for (DayPart dayPart : day.getDayParts()){
            dayPart.setOrdered(dayPart.getOrders().get(userId).getState().equals("Prihlaseno"));
            dayPart.setSelected(dayPart.getOrders().get(userId).getState().equals("Prihlaseno"));

            for(Food food : dayPart.getFoods()){
                if(dayPart.getOrders().get(userId).getFoodId() == food.getId()) {
                    dayPart.selectFood(food);
                    food.setOrdered(true);
                }
            }
        }
    }
}
