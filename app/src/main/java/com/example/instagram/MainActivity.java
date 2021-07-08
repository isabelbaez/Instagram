package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
        } else {
            goLogin();
        }
        super.onCreate(savedInstanceState);

        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                goLogin();
            }
        });
    }

    private void goLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}