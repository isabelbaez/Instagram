package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";

    private EditText etUsername;
    private EditText etPassword;
    private Button btn2Login;
    private Button btn2Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btn2Login = findViewById(R.id.btn2Login);
        btn2Signup = findViewById(R.id.btn2Signup);

        btn2Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "OnClick of Login Button!");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });

        btn2Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSignupActivity();
            }
        });
    }

    private void goSignupActivity() {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.i(TAG, "Issue with Login: " + e, e);
                } else {
                    goFeedActivity();
                    Toast.makeText(LoginActivity.this, "Success!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goFeedActivity() {
        Intent i = new Intent(this, FeedActivity.class);
        startActivity(i);
    }
}