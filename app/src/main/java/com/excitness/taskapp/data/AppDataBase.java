package com.excitness.taskapp.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.excitness.taskapp.Model.TaskModel;

@Database(entities = {TaskModel.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
