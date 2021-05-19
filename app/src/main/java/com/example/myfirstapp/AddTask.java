package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView submit;
    String title, body, state;
    EditText taskTitle, taskBody;
    Button btn;

    ArrayList<Task> tasks;
    int position= 0;
    int count = 1;
    TaskAdapter adapter2;
    AppDatabase appDatabase;
    TaskDao taskDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        submit = findViewById(R.id.textView7);
        submit.setVisibility(View.INVISIBLE);
        btn = findViewById(R.id.button3);
        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"new", "assigned", "in progress", "complete"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(this);
        adapter2 = new TaskAdapter(tasks);

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "task_master").allowMainThreadQueries().build();
        taskDao = appDatabase.taskDao();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        Intent back = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(back);
//        return true;
//        }

    public void submit(View view) {
        submit.setVisibility(View.VISIBLE);

        taskTitle = findViewById(R.id.editTextTextPersonName);
        taskBody = findViewById(R.id.multiline);

        title = taskTitle.getText().toString();
        body = taskBody.getText().toString();

        Task task = new Task(title, body, state );

//        AppDatabase.getInstance(getApplicationContext()).taskDao().insertTask(task);

        taskDao.insertTask(task);
//        Intent intent = new Intent(AddTask.this, MainActivity.class);
//        intent.putExtra("title", title);
//        intent.putExtra("body", body);
//        intent.putExtra("state", state);
//        intent.putExtra("counts", count);
//        tasks = Task.createTasksList(count);
//        tasks.add(position, new Task(title, body, state));
//        adapter2.notifyItemInserted(position);
//
//        position++;
//        count ++;
//        System.out.println("HHHHHHHHH"+tasks);
//        startActivity(intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                state = "new";
                break;
            case 1:
                state = "assigned";
                break;
            case 2:
                state = "in progress";
                break;
            case 3:
                state = "complete";
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

//    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//            AppDatabase.class, "tasks").build();
}