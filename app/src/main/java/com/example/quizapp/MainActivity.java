package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//     Redirect immediately to your custom LandingActivity
        Intent intent = new Intent(MainActivity.this, LandingActivity.class);
        startActivity(intent);

    }
}