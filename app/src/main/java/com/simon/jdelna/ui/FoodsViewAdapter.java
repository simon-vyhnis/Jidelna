package com.simon.jdelna.ui;

import static java.lang.System.currentTimeMillis;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simon.jdelna.R;
import com.simon.jdelna.http.model.DayPart;
import com.simon.jdelna.http.model.Food;

import java.util.List;

public class FoodsViewAdapter extends RecyclerView.Adapter<FoodsViewAdapter.ViewHolder> {
    List<Food> foods;
    MainActivity activity;
    DayPart dayPart;
    String date;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ViewHolder(View view) {
            super(view);
        }

        public void onCheckboxChange(View view) {
            if(view != itemView) {
                CheckBox checkBox = this.itemView.findViewById(R.id.select);
                checkBox.setChecked(false);
            }
        }
    }

    public FoodsViewAdapter(DayPart dayPart, MainActivity activity, String date){
        this.foods = dayPart.getFoods();
        this.activity = activity;
        this.dayPart = dayPart;
        this.date = date;
    }

    @NonNull
    @Override
    public FoodsViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodsViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodsViewAdapter.ViewHolder holder, int position) {
        Food current = foods.get(position);
        TextView title = holder.itemView.findViewById(R.id.title);
        title.setText(current.getName());
        TextView content = holder.itemView.findViewById(R.id.content);
        content.setText(current.getContent());
        CheckBox checkBox = holder.itemView.findViewById(R.id.select);
        checkBox.setOnCheckedChangeListener(null);

        if(!dayPart.getOrders().get(Integer.toString(activity.getUserId())).getState().equals("Prihlaseno")){
            holder.itemView.setBackgroundColor(Color.parseColor("#ffc4cf"));
        }else {
            if (!dayPart.isFoodSelected(current)) {
                checkBox.setChecked(false);
            }

            //coloring selected food
            if (dayPart.getOrders().get(Integer.toString(activity.getUserId())).getFoodId() == current.getId()) {
                holder.itemView.setBackgroundColor(Color.parseColor("#b8ffc8"));
                if (dayPart.getSelectedFood() == null) {
                    dayPart.selectFood(current);
                    checkBox.setChecked(true);
                }
            }
        }

        checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if(b) {
                dayPart.selectFood(current);
            }else{
                dayPart.setOrdered(false);
                for(Food food : dayPart.getFoods()){
                    if (dayPart.getOrders().get(Integer.toString(activity.getUserId())).getFoodId() == food.getId()) {
                        dayPart.selectFood(food);
                    }
                }
            }
            activity.addOrderChange(dayPart, date);
            notifyDataSetChanged();
        });
        //POST https://www.jidelna.cz/rest/u/c58zbtfnjz72h6t5nzfva9uzvbag8m/zarizeni/177/objednavky
        //[{"idUzivatele":"2559062","idMenu":"12391","den":"2021-12-02","stav":"Prihlaseno","mnozstvi":1}]
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

}