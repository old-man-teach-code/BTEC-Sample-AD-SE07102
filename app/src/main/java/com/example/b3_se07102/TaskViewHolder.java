package com.example.b3_se07102;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// Dùng để tham chiếu đến Item trong View
public class TaskViewHolder extends RecyclerView.ViewHolder
{
    public TextView tvTaskName;
    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTaskName = itemView.findViewById(R.id.tvTaskName);
    }
}
