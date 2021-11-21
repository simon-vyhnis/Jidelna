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

public class DaysViewAdapter extends RecyclerView.Adapter<DaysViewAdapter.ViewHolder>{
    List<DailyMenu> days;
    MainActivity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View view) {
            super(view);
        }
    }

    public DaysViewAdapter(List<DailyMenu> days, MainActivity activity){
        this.days = days;
        this.activity = activity;
    }

    @NonNull
    @Override
    public DaysViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        return new DaysViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysViewAdapter.ViewHolder holder, int position) {
        TextView date = holder.itemView.findViewById(R.id.date);
        if(days.get(position).getDate() != null) {
            date.setText(days.get(position).getDate());
        }
        DayPartsViewAdapter adapter = new DayPartsViewAdapter(days.get(position).getDay().getDayParts(), activity);
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.day_parts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

}