package com.excitness.taskapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.excitness.taskapp.Model.TaskModel;

import java.util.List;

@Dao
public interface TaskDao{

    @Query("SELECT * FROM taskmodel")
    List<TaskModel> getAllTasks();

    @Query("SELECT * FROM taskmodel ORDER BY title ASC")
    List<TaskModel> getAllTasksAlph();

    @Query("SELECT * FROM taskmodel ORDER BY created ASC")
    List<TaskModel> getAllTasksDate();

    @Insert
    void addTask(TaskModel taskModel);

    @Update
    void update(TaskModel taskModel);

    @Delete
    void delete(TaskModel taskModel);
}

