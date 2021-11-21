package com.simon.jdelna.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simon.jdelna.R;
import com.simon.jdelna.http.DailyMenu;

import java.util.List;

public class DayPartsViewAdapter extends RecyclerView.Adapter<DayPartsViewAdapter.ViewHolder>{
    List<DailyMenu.DayPart> dayParts;
    private MainActivity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View view) {
            super(view);
        }
    }

    public DayPartsViewAdapter(List<DailyMenu.DayPart> dayParts, MainActivity activity){
        this.dayParts = dayParts;
        this.activity = activity;
    }

    @NonNull
    @Override
    public DayPartsViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_part, parent, false);
        return new DayPartsViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayPartsViewAdapter.ViewHolder holder, int position) {
        TextView title = holder.itemView.findViewById(R.id.title);
        title.setText(dayParts.get(position).getTitle());
        FoodsViewAdapter adapter = new FoodsViewAdapter(dayParts.get(position).getFoods(), activity);
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.foods);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }

    @Override
    public int getItemCount() {
        return dayParts.size();
    }

}