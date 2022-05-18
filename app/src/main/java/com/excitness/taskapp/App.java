package com.excitness.taskapp;

import android.app.Application;

import androidx.room.Room;

import com.excitness.taskapp.data.AppDataBase;
import com.excitness.taskapp.utils.Prefs;

public class App extends Application {
    public static Prefs prefs;
    public static AppDataBase dataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        prefs = new Prefs(getApplicationContext());
        dataBase = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "database"
        ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
