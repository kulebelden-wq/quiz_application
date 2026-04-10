package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvScore = findViewById(R.id.tvScore);
        TextView tvScoreDetail = findViewById(R.id.tvScoreDetail);
        TextView tvCategory = findViewById(R.id.tvCategory);
        Button btnMainMenu = findViewById(R.id.btnMainMenu);
        Button btnRestart = findViewById(R.id.btnNext);


    }
}