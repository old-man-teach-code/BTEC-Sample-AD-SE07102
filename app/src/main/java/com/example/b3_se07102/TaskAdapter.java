package com.example.b3_se07102;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder>
{
    private ArrayList<String> taskList; // Danh sách các task

    public TaskAdapter(ArrayList<String> taskList) {
        this.taskList = taskList; // Khởi tạo danh sách các task
    }

    @NonNull
    @Override // Khởi tạo ViewHolder từ layout TaskViewHolder
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Khởi tạo ViewHolder bằng cách lấy layoutInflater (lấy layout từ xml) - layout của item
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Gắn layout cho ViewHolder
        View itemView = layoutInflater.inflate((R.layout.item_task), parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override // Gắn dữ liệu vào ViewHolder (nội dung của task item)
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        // Lấy dữ liệu từ ArrayList
        String task = taskList.get(position);
        // Gắn dữ liệu vào ViewHolder
        holder.tvTaskName.setText(task);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
