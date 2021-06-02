package com.example.myfirstapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.File;
import java.util.ArrayList;

//@Fts4(languageId = "lid")
@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "task_title")
    String title;

    @ColumnInfo(name = "task_body")
    String body;

    @ColumnInfo(name = "task_state")
    String state;

    String file;

//    @ColumnInfo(name = "lid")
//    int languageId;

    public Task(String title, String body, String state, String file) {
        this.title = title;
        this.body = body;
        this.state = state;
        this.file = file;
    }

//    public Task(String title, String body, String state) {
//        this.title = title;
//        this.body = body;
//        this.state = state;
//    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static ArrayList<Task> createTasksList(int taskCount) {
        ArrayList<Task> tasks = new ArrayList<Task>();

//        for (int i = 1; i <= taskCount; i++) {
//            tasks.add(new Task("Task Title: " , "Task Body: " , "Task State: "));
//        }
        return tasks;
    }

//    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//            AppDatabase.class, "tasks").build();
}
