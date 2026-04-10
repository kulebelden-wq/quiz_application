package com.example.quizapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView tvQuestion, tvQuestionNo, tvCategoryTitle, tvScenario, tvExplanationText;
    private ProgressBar progressBar;
    private Button btnOp1, btnOp2, btnOp3, btnOp4, btnNext;
    private LinearLayout layoutExplanation, btnToggleExplanation;
    private ImageView ivExplanationArrow;

    private List<Question> filteredQuestions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int selectedOptionIndex = -1;
    private boolean isAnswered = false;
    private boolean isExplanationExpanded = false;
    private String selectedCategory;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        selectedCategory = getIntent().getStringExtra("CATEGORY");
        dbHelper = new DatabaseHelper(this);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNo = findViewById(R.id.tvQuestionNo);
        tvCategoryTitle = findViewById(R.id.tvCategoryTitle);
        tvScenario = findViewById(R.id.tvScenario);
        tvExplanationText = findViewById(R.id.tvExplanationText);
        progressBar = findViewById(R.id.progressBar);
        btnOp1 = findViewById(R.id.btnOption1);
        btnOp2 = findViewById(R.id.btnOption2);
        btnOp3 = findViewById(R.id.btnOption3);
        btnOp4 = findViewById(R.id.btnOption4);
        btnNext = findViewById(R.id.btnNext);
        layoutExplanation = findViewById(R.id.layoutExplanation);
        btnToggleExplanation = findViewById(R.id.btnToggleExplanation);
        ivExplanationArrow = findViewById(R.id.ivExplanationArrow);

        tvCategoryTitle.setText(selectedCategory + " Quiz");

        loadQuestionsFromDb();
        
        if (filteredQuestions.isEmpty()) {
            Toast.makeText(this, "No questions found for this category!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        
        displayQuestion();

        btnToggleExplanation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isExplanationExpanded = !isExplanationExpanded;
                tvExplanationText.setVisibility(isExplanationExpanded ? View.VISIBLE : View.GONE);
                ivExplanationArrow.setRotation(isExplanationExpanded ? 180 : 0);
            }
        });

        View.OnClickListener optionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnswered) return;
                
                resetOptionButtons();
                v.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.orange_primary)));
                
                if (v.getId() == R.id.btnOption1) selectedOptionIndex = 0;
                else if (v.getId() == R.id.btnOption2) selectedOptionIndex = 1;
                else if (v.getId() == R.id.btnOption3) selectedOptionIndex = 2;
                else if (v.getId() == R.id.btnOption4) selectedOptionIndex = 3;
            }
        };

        btnOp1.setOnClickListener(optionClickListener);
        btnOp2.setOnClickListener(optionClickListener);
        btnOp3.setOnClickListener(optionClickListener);
        btnOp4.setOnClickListener(optionClickListener);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionIndex == -1) {
                    Toast.makeText(QuizActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isAnswered) {
                    checkAnswer();
                } else {
                    currentQuestionIndex++;
                    if (currentQuestionIndex < filteredQuestions.size()) {
                        displayQuestion();
                    } else {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        intent.putExtra("SCORE", score);
                        intent.putExtra("TOTAL", filteredQuestions.size());
                        intent.putExtra("CATEGORY", selectedCategory);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }

    private void loadQuestionsFromDb() {
        filteredQuestions = dbHelper.getQuestionsByCategory(selectedCategory);
    }

    private void displayQuestion() {
        isAnswered = false;
        selectedOptionIndex = -1;
        isExplanationExpanded = false;
        btnNext.setText("CHECK");
        resetOptionButtons();
        
        layoutExplanation.setVisibility(View.GONE);
        tvExplanationText.setVisibility(View.GONE);
        ivExplanationArrow.setRotation(0);

        Question currentQuestion = filteredQuestions.get(currentQuestionIndex);
        
        if (currentQuestion.hasScenario()) {
            tvScenario.setVisibility(View.VISIBLE);
            tvScenario.setText(currentQuestion.getScenario());
        } else {
            tvScenario.setVisibility(View.GONE);
        }

        tvQuestion.setText(currentQuestion.getQuestion());
        btnOp1.setText(currentQuestion.getOption1());
        btnOp2.setText(currentQuestion.getOption2());
        btnOp3.setText(currentQuestion.getOption3());
        btnOp4.setText(currentQuestion.getOption4());
        tvExplanationText.setText(currentQuestion.getExplanation());

        String qNo = String.format("%02d", currentQuestionIndex + 1);
        tvQuestionNo.setText("Question " + qNo + "/" + filteredQuestions.size());
        progressBar.setProgress((currentQuestionIndex + 1) * 100 / filteredQuestions.size());
    }

    private void checkAnswer() {
        isAnswered = true;
        Question currentQuestion = filteredQuestions.get(currentQuestionIndex);
        int correctIndex = currentQuestion.getAnswerIndex();

        if (selectedOptionIndex == correctIndex) {
            score++;
            highlightButton(selectedOptionIndex, R.color.green_correct);
        } else {
            highlightButton(selectedOptionIndex, R.color.red_wrong);
            highlightButton(correctIndex, R.color.green_correct);
        }

        layoutExplanation.setVisibility(View.VISIBLE);
        btnNext.setText(currentQuestionIndex == filteredQuestions.size() - 1 ? "FINISH" : "NEXT");
    }

    private void highlightButton(int index, int colorRes) {
        Button btn = null;
        if (index == 0) btn = btnOp1;
        else if (index == 1) btn = btnOp2;
        else if (index == 2) btn = btnOp3;
        else if (index == 3) btn = btnOp4;

        if (btn != null) {
            btn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(colorRes)));
        }
    }

    private void resetOptionButtons() {
        ColorStateList darkColor = ColorStateList.valueOf(getResources().getColor(R.color.card_dark));
        btnOp1.setBackgroundTintList(darkColor);
        btnOp2.setBackgroundTintList(darkColor);
        btnOp3.setBackgroundTintList(darkColor);
        btnOp4.setBackgroundTintList(darkColor);
    }
}
