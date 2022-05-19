package com.excitness.taskapp.ui.home;

import android.annotation.SuppressLint;
import android.app.MediaRouteButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.excitness.taskapp.Model.TaskModel;
import com.excitness.taskapp.databinding.ItemRvBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private final List<TaskModel> list = new ArrayList<>();
    private ItemClick itemClick;

    public TaskAdapter(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    //@SuppressLint("NotifyDataSetChanged")
    //public void addList(TaskModel model){
      //  this.list.add(model);
        //notifyDataSetChanged();
    //}

    public TaskModel getItem(int pos){
        return list.get(pos);
    }

    public void delete(int pos){
        list.remove(pos);
        notifyItemRemoved(pos);
    }

    public void addList(List<TaskModel>list){
        this.list.clear();
        this.list.addAll(list);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBind(list.get(position), itemClick);
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

        }

        public void onBind(TaskModel taskModel, ItemClick itemClick) {
            binding.taskRv.setText(taskModel.getTitle());
            binding.created.setText(taskModel.getCreated());
            binding.getRoot().setOnClickListener(v-> itemClick.click(getAdapterPosition()));
            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    itemClick.longClick(getAdapterPosition());
                    return true;
                }
            });
        }
    }

    interface ItemClick{
        void click(int pos);
        void longClick(int pos);
    }

}
