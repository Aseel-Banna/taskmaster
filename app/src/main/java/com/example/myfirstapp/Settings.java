package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

    Button save;
    String nameOfuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        nameOfuser = sharedPreferences.getString("username", "User");
        save = findViewById(R.id.button4);
        save.setOnClickListener((view) ->{
            EditText username= findViewById(R.id.editTextTextPersonName3);
            nameOfuser = username.getText().toString();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", nameOfuser);
            editor.apply();
            Intent main = new Intent(Settings.this, MainActivity.class);
            startActivity(main);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent back = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(back);
        return true;
    }
}