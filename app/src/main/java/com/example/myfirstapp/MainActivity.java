package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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

//        if(Amplify.Auth.getCurrentUser() == null) {
            welcome_user.setText(sharedPreferences.getString("username", "User") + "'s Tasks");

//        }else{
//            welcome_user.setText(Amplify.Auth.getCurrentUser().getUsername() + "'s Tasks");
//        }


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FCM token ...", "Fetching FCM is failed", task.getException());
                            return;
                        }
                        String token = task.getResult();
                        Log.d("FCM TOKEN ...",task.getResult());
                    }
                });


        try {
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());
            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }

        Amplify.Auth.signInWithWebUI(this,
                result -> Log.i("AuthQuickStart", result.toString()),
                error ->  Log.i("AuthQuickStart", error.toString()));
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

    public void goToSignUp(View view) {
        Intent signup = new Intent( MainActivity.this, SignUp.class);
        startActivity(signup);
    }

    public void goToLogin(View view) {
        Intent signup = new Intent( MainActivity.this, Login.class);
        startActivity(signup);
    }

    public void goToSignOut(View view) {
        Amplify.Auth.signOut(
                () -> Log.i("AuthQuickstart", "Signed out successfully"),
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AWSCognitoAuthPlugin.WEB_UI_SIGN_IN_ACTIVITY_CODE) {
            Amplify.Auth.handleWebUISignInResponse(data);
        }
    }

    protected void uploadFile(Context context){
        File file = new File(context.getFilesDir(), "key0");

        try{
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.append("test");
            bufferedWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        Amplify.Storage.uploadFile(
                "key0",
                file,
                result -> Log.i("uploadFile", "Successfully Uploaded: "+ result.getKey()),
                error -> Log.e("uploadFile", "Storage Failure: "+error)
        );

    }


}