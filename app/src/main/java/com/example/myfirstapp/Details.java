package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    TextView welcome, paragraph, state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        welcome = findViewById(R.id.textView6);
        paragraph = findViewById(R.id.textView8);
        state = findViewById(R.id.textView10);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String title = getIntent().getStringExtra("title");
        String body = getIntent().getStringExtra("body");
        String theState = getIntent().getStringExtra("state");

        welcome.setText(sharedPreferences.getString("username", "User")+"'s "+ title + " Detail");
        paragraph.setText(body);
        state.setText(theState);
        if(title==null){
            welcome.setText(sharedPreferences.getString("username", "User")+"'s Task Detail");
            paragraph.setText("No details");
            state.setText("No State");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle( title +" Details");

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent back = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(back);
        return true;
    }

    public void addTaskPage(View view) {
        Intent add = new Intent( Details.this, AddTask.class);
        startActivity(add);
    }
}