package com.simon.jdelna.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simon.jdelna.R;
import com.simon.jdelna.http.User;

import java.util.List;

public class UserListViewAdapter extends RecyclerView.Adapter<UserListViewAdapter.ViewHolder>{
    List<User> users;
    ChooseUserActivity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View view) {
            super(view);
        }
    }

    public UserListViewAdapter(List<User> users, ChooseUserActivity activity){
        this.users = users;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView name = holder.itemView.findViewById(R.id.name);
        name.setText(users.get(position).getName());
        holder.itemView.setOnClickListener((v)->{
            activity.onUserSelected(users.get(position).getUserId());
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}
