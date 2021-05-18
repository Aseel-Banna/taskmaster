package com.example.myfirstapp;

import java.util.ArrayList;

public class Task {
    String title;
    String body;
    String state;

    public Task(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;
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
}
