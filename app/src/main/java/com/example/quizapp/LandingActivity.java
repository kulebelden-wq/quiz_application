package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LandingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Button btnStartPlaying = findViewById(R.id.btnStartPlaying);

        btnStartPlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the new full-page Category Activity
                Intent intent = new Intent(LandingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
