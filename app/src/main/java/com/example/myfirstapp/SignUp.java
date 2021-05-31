package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class SignUp extends AppCompatActivity {

    EditText username, email, password;
    String nameSave, emailSave, passwordSave;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.userNameInput);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        signUp = findViewById(R.id.signUp);

        nameSave = username.getText().toString();
        emailSave = email.getText().toString();
        passwordSave = password.getText().toString();
    }


    public void signup(View view) {
        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.email(), emailSave)
                .build();
        Amplify.Auth.signUp(nameSave, passwordSave, options,
                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
                error -> Log.e("AuthQuickStart", "Sign up failed", error)
        );
        Intent intent = new Intent(SignUp.this, EmailCong.class);
        intent.putExtra("username", nameSave);
        startActivity(intent);

    }
}