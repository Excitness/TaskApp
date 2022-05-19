package com.excitness.taskapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class TaskModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String created;

    public TaskModel() {
    }

    public TaskModel(String title, String created) {
        this.title = title;
        this.created = created;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public String getCreated() {
        return created;
    }
}
