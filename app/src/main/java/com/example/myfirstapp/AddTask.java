package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskEntity;

import java.util.ArrayList;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Task");
        btn = findViewById(R.id.button3);
        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"new", "assigned", "in progress", "complete"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(this);
        adapter2 = new TaskAdapter(tasks);

//        appDatabase = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "task_master").allowMainThreadQueries().build();
//        taskDao = appDatabase.taskDao();

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

        TaskEntity item = TaskEntity.builder()
                .title(title)
                .body(body)
                .state(state)
                .build();

        Amplify.DataStore.save(item,
                success -> Log.i("Tutorial", "Saved item: " + success.item().getTitle() + " This is body "+success.item().getBody()),
                error -> Log.e("Tutorial", "Could not save item to DataStore", error)
        );

        Amplify.DataStore.query(TaskEntity.class,
                tasks -> {
                    while (tasks.hasNext()) {
                        TaskEntity taskEntity = tasks.next();

                        Log.i("Tutorial", "==== Todo ====");
                        Log.i("Tutorial", "Title: " + title);

                        if (task.getBody() != null) {
                            Log.i("Tutorial", "Body: " + body);
                        }

                        if (task.getState() != null) {
                            Log.i("Tutorial", "State: " + state);
                        }
                    }
                },
                failure -> Log.e("Tutorial", "Could not query DataStore", failure)
        );

//        AppDatabase.getInstance(getApplicationContext()).taskDao().insertTask(task);

//        taskDao.insertTask(task);
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