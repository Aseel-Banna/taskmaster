package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.ListItemClickListener {

    Button addTask, allTasks, task1, task2, task3;
    String title1, title2, title3;
    TextView welcome_user;
    TaskDao taskDao;

    private TextView taskTitle;
    private TextView taskBody;
    private TextView taskState;

    String title, body, state;
    RecyclerView rvTasks;

    int count;

    ArrayList<Task> taskModels;
    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addTask = findViewById(R.id.button);
        allTasks = findViewById(R.id.button2);

        welcome_user = findViewById(R.id.user_welcome);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        welcome_user.setText(sharedPreferences.getString("username", "User") + "'s Tasks");

        db= Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "task_master").allowMainThreadQueries().build();

        taskTitle = findViewById(R.id.taskTitle);
        taskBody = findViewById(R.id.taskBody);
        taskState =findViewById(R.id.taskState);

        rvTasks = findViewById(R.id.recyclerView);

        taskDao = db.taskDao();
        taskModels =  (ArrayList<Task>) taskDao.getAll();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTasks.setLayoutManager(layoutManager);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(new TaskAdapter(taskModels, this));

        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }

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

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        taskModels = (ArrayList<Task>) taskDao.getAll();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTasks.setLayoutManager(layoutManager);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(new TaskAdapter(taskModels, this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        taskModels = (ArrayList<Task>) taskDao.getAll();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTasks.setLayoutManager(layoutManager);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(new TaskAdapter(taskModels, this));
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        taskModels = (ArrayList<Task>) taskDao.getAll();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvTasks.setLayoutManager(layoutManager);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(new TaskAdapter(taskModels, this));
    }
}