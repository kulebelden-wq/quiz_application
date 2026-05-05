package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        LinearLayout btnTech = findViewById(R.id.btnSelectTech);
        LinearLayout btnMath = findViewById(R.id.btnSelectMath);
        LinearLayout btnPhysics = findViewById(R.id.btnSelectPhysics);
        LinearLayout btnRiddles = findViewById(R.id.btnSelectRiddles);
        LinearLayout btnIQ = findViewById(R.id.btnSelectIQ);
        LinearLayout btnCustom = findViewById(R.id.btnSelectCustom);
        Button btnCreate = findViewById(R.id.btnGoToCreate);

        btnTech.setOnClickListener(v -> startQuiz("Technology"));
        btnMath.setOnClickListener(v -> startQuiz("Mathematics"));
        btnPhysics.setOnClickListener(v -> startQuiz("Physics"));
        btnRiddles.setOnClickListener(v -> startQuiz("Riddles"));
        btnIQ.setOnClickListener(v -> startQuiz("IQ Test"));
        btnCustom.setOnClickListener(v -> startQuiz("Custom"));
        
        btnCreate.setOnClickListener(v -> {
            Intent intent = new Intent(CategoryActivity.this, CreateQuizActivity.class);
            startActivity(intent);
        });
    }

    private void startQuiz(String category) {
        Intent intent = new Intent(CategoryActivity.this, QuizActivity.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }
}
