package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addTask, allTasks, task1, task2, task3;
    String title1, title2, title3;
    TextView welcome_user;

    private TextView taskTitle;
    private TextView taskBody;
    private TextView taskState;

    String title, body, state;

    ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addTask = findViewById(R.id.button);
        allTasks = findViewById(R.id.button2);

        welcome_user = findViewById(R.id.user_welcome);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        welcome_user.setText(sharedPreferences.getString("username", "User") + "'s Tasks");

        taskTitle = findViewById(R.id.taskTitle);
        taskBody = findViewById(R.id.taskBody);
        taskState =findViewById(R.id.taskState);

        RecyclerView rvTasks = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTasks.setLayoutManager(layoutManager);

        tasks = Task.createTasksList(3);
        TaskAdapter adapter = new TaskAdapter(tasks);
        rvTasks.setAdapter(adapter);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));

        tasks.add(0, new Task("Task 1", "It is the first task I have created", "complete"));
        adapter.notifyItemInserted(0);

        tasks.add(1, new Task("Task 2", "It is the second task I have created", "in progress"));
        adapter.notifyItemInserted(1);

        tasks.add(2, new Task("Task 3", "It is the third task I have created", "new"));
        adapter.notifyItemInserted(2);


    }

    public void addTaskPage(View view) {
        Intent add = new Intent( MainActivity.this, AddTask.class);
        startActivity(add);
    }

    public void allTasksPage(View view) {
        Intent all = new Intent( MainActivity.this, AllTasks.class);
        startActivity(all);
    }

    public void goToSettings(View view) {
        Intent settings = new Intent( MainActivity.this, Settings.class);
        startActivity(settings);
    }

    public void goToDetails(View view) {
        Intent details = new Intent( MainActivity.this, Details.class);
        startActivity(details);
    }

}