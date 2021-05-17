package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button addTask, allTasks, task1, task2, task3;
    String title1, title2, title3;
    TextView welcome_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addTask = findViewById(R.id.button);
        allTasks = findViewById(R.id.button2);
        task1 = findViewById(R.id.button5);
        task2 = findViewById(R.id.button6);
        task3 = findViewById(R.id.button7);

        welcome_user = findViewById(R.id.user_welcome);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        welcome_user.setText(sharedPreferences.getString("username", "User") + "'s Tasks");

        task1.setText("Task 1");
        task2.setText("Task 2");
        task3.setText("Task 3");

        title1= task1.getText().toString();
        title2= task2.getText().toString();
        title3= task3.getText().toString();

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

    public void taskDetails(View view) {
        Intent details = new Intent( MainActivity.this, Details.class);
        details.putExtra("title", title1);
        startActivity(details);
    }

    public void taskDetails2(View view) {
        Intent details = new Intent( MainActivity.this, Details.class);
        details.putExtra("title", title2);
        startActivity(details);
    }

    public void taskDetails3(View view) {
        Intent details = new Intent( MainActivity.this, Details.class);
        details.putExtra("title", title3);
        startActivity(details);
    }
}