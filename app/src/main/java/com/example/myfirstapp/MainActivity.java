package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addTask, allTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addTask = findViewById(R.id.button);
        allTasks = findViewById(R.id.button2);
    }

    public void addTaskPage(View view) {
        Intent add = new Intent( MainActivity.this, addTask.class);
        startActivity(add);
    }

    public void allTasksPage(View view) {
        Intent all = new Intent( MainActivity.this, allTasks.class);
        startActivity(all);
    }
}