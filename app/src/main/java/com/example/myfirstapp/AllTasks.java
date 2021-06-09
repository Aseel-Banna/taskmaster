package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class AllTasks extends AppCompatActivity implements TaskAdapter.ListItemClickListener {

    RecyclerView rvTasks;

    TaskDao taskDao;

    ArrayList<Task> tasks;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Tasks");

        db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "task_master").allowMainThreadQueries().build();

        rvTasks = findViewById(R.id.recyclerView);

        taskDao = db.taskDao();
        tasks = (ArrayList<Task>) taskDao.getAll();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTasks.setLayoutManager(layoutManager);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(new TaskAdapter(tasks, this));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent back = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(back);
        return true;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}