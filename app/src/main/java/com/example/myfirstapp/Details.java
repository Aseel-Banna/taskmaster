package com.example.myfirstapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Details extends AppCompatActivity {

    TextView welcome, paragraph, state, file;
    ImageView i;

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

        i = findViewById(R.id.imageView3);
        i.setVisibility(View.INVISIBLE);
        file = findViewById(R.id.textView4);
        file.setVisibility(View.INVISIBLE);

        welcome.setText(sharedPreferences.getString("username", "User")+"'s "+ title + " Detail");
        paragraph.setText(body);
        state.setText(theState);
        if(title==null){
            welcome.setText(sharedPreferences.getString("username", "User")+"'s Task Detail");
            paragraph.setText("No details");
            state.setText("No State");
        }

        String fileName = getIntent().getStringExtra("file");
        if (fileName.endsWith("jpg")  || fileName.endsWith("png") || fileName.endsWith("gif")  || fileName.endsWith("jpeg") ){
            i.setVisibility(View.VISIBLE);
            i.setImageBitmap(BitmapFactory.decodeFile(fileName));
        }else {
            file.setVisibility(View.VISIBLE);
            file.setText(fileName);
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