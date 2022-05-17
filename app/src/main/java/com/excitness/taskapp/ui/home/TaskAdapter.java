package com.excitness.taskapp.ui.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.excitness.taskapp.Model.TaskModel;
import com.excitness.taskapp.databinding.ItemRvBinding;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {


    private final List<TaskModel> list = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void addList(TaskModel lists){
        this.list.add(lists);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRvBinding binding = ItemRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position).getTitle(), list.get(position).getCreated());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemRvBinding binding;
        public ViewHolder(@NonNull ItemRvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
    //пишу код и люблю тебя

        }

        public void onBind(String title, String created) {
            binding.taskRv.setText(title);
            binding.created.setText(created);
        }
    }

}
