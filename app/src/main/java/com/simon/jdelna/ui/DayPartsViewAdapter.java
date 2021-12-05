package com.simon.jdelna.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simon.jdelna.R;
import com.simon.jdelna.http.model.DayPart;
import com.simon.jdelna.http.model.DayWrap;

import java.util.List;

public class DayPartsViewAdapter extends RecyclerView.Adapter<DayPartsViewAdapter.ViewHolder>{
    List<DayPart> dayParts;
    private MainActivity activity;
    private String date;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View view) {
            super(view);
        }
    }

    public DayPartsViewAdapter(DayWrap dayWrap, MainActivity activity){
        this.dayParts = dayWrap.getDay().getDayParts();
        this.date = dayWrap.getDate();
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
        DayPart current = dayParts.get(position);
        TextView title = holder.itemView.findViewById(R.id.title);
        title.setText(current.getTitle());
        FoodsViewAdapter adapter = new FoodsViewAdapter(current, activity, date);
        RecyclerView recyclerView = holder.itemView.findViewById(R.id.foods);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }

    @Override
    public int getItemCount() {
        return dayParts.size();
    }

}