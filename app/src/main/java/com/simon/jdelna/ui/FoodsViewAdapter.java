package com.simon.jdelna.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simon.jdelna.R;
import com.simon.jdelna.http.DailyMenu;

import java.util.List;

public class FoodsViewAdapter extends RecyclerView.Adapter<FoodsViewAdapter.ViewHolder>{
    List<DailyMenu.DayPart.Food> foods;
    MainActivity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View view) {
            super(view);
        }
    }

    public FoodsViewAdapter(List<DailyMenu.DayPart.Food> foods, MainActivity activity){
        this.foods = foods;
        this.activity = activity;
    }

    @NonNull
    @Override
    public FoodsViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodsViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodsViewAdapter.ViewHolder holder, int position) {
        TextView title = holder.itemView.findViewById(R.id.title);
        title.setText(foods.get(position).getName());
        TextView content = holder.itemView.findViewById(R.id.content);
        content.setText(foods.get(position).getContent());
        System.out.println("Food added, title: "+foods.get(position).getName());
        //TODO: create onClick for selecting here
        //TODO: color the layout
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

}