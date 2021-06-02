package com.example.myfirstapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskEntity;
import com.amplifyframework.storage.options.StorageUploadFileOptions;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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

    String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Task");
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

        try {
            // Add these lines to add the AWSCognitoAuthPlugin and AWSS3StoragePlugin plugins
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }


    }

    public void submit(View view) {
//        submit.setVisibility(View.VISIBLE);

        taskTitle = findViewById(R.id.editTextTextPersonName);
        taskBody = findViewById(R.id.multiline);

        title = taskTitle.getText().toString();
        body = taskBody.getText().toString();

        Task task = new Task(title, body, state, filePath );
//
        TaskEntity item = TaskEntity.builder()
                .title(title)
                .body(body)
                .state(state)
                .build();

        Amplify.DataStore.save(item,
                success -> Log.i("Tutorial", "Saved item: " + success.item().getTitle()),
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


    public void uploadFile(File file, String fileName) {
        Amplify.Storage.uploadFile(
                fileName,
                file,
                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );
    }

    public void getFileFromMobileStorage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,RESULT_OK);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AWSCognitoAuthPlugin.WEB_UI_SIGN_IN_ACTIVITY_CODE){
            Amplify.Auth.handleWebUISignInResponse(data);
        }

        if (requestCode == RESULT_OK){
            File file = new File(getApplicationContext().getFilesDir(), "uploads");
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    FileUtils.copy(inputStream, new FileOutputStream(file));
                    uploadFile(file, file.getName());
                    filePath = file.getName();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        }

    public void getFile(View view) {
        getFileFromMobileStorage();
    }
}