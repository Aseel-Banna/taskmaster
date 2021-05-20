package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    TextView welcome, paragraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        welcome = findViewById(R.id.textView6);
        paragraph = findViewById(R.id.textView8);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String title = getIntent().getStringExtra("title");
        welcome.setText(sharedPreferences.getString("username", "User")+"'s "+ title + " Detail");
        paragraph.setText(" Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        if(title==null){
            welcome.setText(sharedPreferences.getString("username", "User")+"'s Task Detail");
            paragraph.setText("No details");
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
}