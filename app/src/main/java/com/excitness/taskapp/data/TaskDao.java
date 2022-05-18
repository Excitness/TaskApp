package com.excitness.taskapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.excitness.taskapp.Model.TaskModel;

import java.util.List;

@Dao
public interface TaskDao{

    @Query("SELECT * FROM taskmodel")
    List<TaskModel> getAllTasks();

    @Insert
    void addTask(TaskModel taskModel);
}

