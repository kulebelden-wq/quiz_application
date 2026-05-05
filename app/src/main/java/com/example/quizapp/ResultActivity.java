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
        TextView tvPoints = findViewById(R.id.tvPoints);
        TextView tvStatus = findViewById(R.id.tvStatus);
        Button btnMainMenu = findViewById(R.id.btnMainMenu);
        Button btnRestart = findViewById(R.id.btnNext);

        // Get data from Intent
        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 0);
        String category = getIntent().getStringExtra("CATEGORY");

        // Calculate values
        int percentage = (total > 0) ? (score * 100 / total) : 0;
        int pointsEarned = score * 10;

        // Update UI
        tvCategory.setText(category + " Quiz");
        tvScore.setText(percentage + "%");
        tvScoreDetail.setText(score + "/" + total + " correct");
        tvPoints.setText("+" + pointsEarned);

        if (percentage >= 50) {
            tvStatus.setText("✅ Passed!");
            tvStatus.setTextColor(getResources().getColor(R.color.green_correct));
        } else {
            tvStatus.setText("❌ Try Again!");
            tvStatus.setTextColor(getResources().getColor(R.color.red_wrong));
        }

         btnMainMenu.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, CategoryActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            intent.putExtra("CATEGORY", category);
            startActivity(intent);
            finish();
        });
    }
}
