package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class EmailCong extends AppCompatActivity {

    EditText code;
    Button confirm;
    String userName, codeSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_cong);

        code = findViewById(R.id.code);
        confirm = findViewById(R.id.confirmEmail);

        codeSave = code.getText().toString();
        userName = getIntent().getExtras().get("username").toString();

    }

    public void confirmEmail(View view) {
        Amplify.Auth.confirmSignUp(
                userName,
                codeSave,
                result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
                error -> Log.e("AuthQuickstart", error.toString())
        );
    Intent intent = new Intent(this, Home.class);
    startActivity(intent);

    }
}