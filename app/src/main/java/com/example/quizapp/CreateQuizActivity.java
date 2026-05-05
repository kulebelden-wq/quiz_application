package com.example.quizapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import org.json.JSONObject;

public class CreateQuizActivity extends AppCompatActivity {

    private EditText etScenario, etQuestion, etOp1, etOp2, etOp3, etOp4, etCorrectIndex, etExplanation;
    private Button btnSave, btnShare, btnScan;
    private ImageView ivQRCode;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        dbHelper = new DatabaseHelper(this);

        etScenario = findViewById(R.id.etScenario);
        etQuestion = findViewById(R.id.etQuestion);
        etOp1 = findViewById(R.id.etOp1);
        etOp2 = findViewById(R.id.etOp2);
        etOp3 = findViewById(R.id.etOp3);
        etOp4 = findViewById(R.id.etOp4);
        etExplanation = findViewById(R.id.etExplanation);
        etCorrectIndex = findViewById(R.id.etCorrectIndex);
        btnSave = findViewById(R.id.btnSaveQuestion);
        btnShare = findViewById(R.id.btnShareQR);
        btnScan = findViewById(R.id.btnScanQR);
        ivQRCode = findViewById(R.id.ivQRCode);

        btnSave.setOnClickListener(v -> saveQuestion());
        
        btnShare.setOnClickListener(v -> generateQRCode());

        btnScan.setOnClickListener(v -> {
            ScanOptions options = new ScanOptions();
            options.setPrompt("Scan Quizard QR Code");
            options.setBeepEnabled(true);
            options.setOrientationLocked(true);
            options.setCaptureActivity(CustomScannerActivity.class);
            barcodeLauncher.launch(options);
        });
    }


    // code scanner launcher(robot)
    private final androidx.activity.result.ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() != null) {
                    importQuestionFromQR(result.getContents());
                }
            });

    private void saveQuestion() {
        Question newQ = getQuestionFromFields();
            if (newQ == null) {
            Toast.makeText(this, "Please fill in all required fields and valid correct index (0-3)", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            dbHelper.addQuestion(newQ);
            Toast.makeText(this, "Question Saved to Database!", Toast.LENGTH_SHORT).show();
            clearFields();
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private Question getQuestionFromFields() {
        try {
            String questionText = etQuestion.getText().toString().trim();
            String op1 = etOp1.getText().toString().trim();
            String op2 = etOp2.getText().toString().trim();
            String op3 = etOp3.getText().toString().trim();
            String op4 = etOp4.getText().toString().trim();
            String correctIdxStr = etCorrectIndex.getText().toString().trim();

            if (questionText.isEmpty() || op1.isEmpty() || op2.isEmpty() || op3.isEmpty() || op4.isEmpty() || correctIdxStr.isEmpty()) {
                return null;
            }

            int correctIndex = Integer.parseInt(correctIdxStr);
            if (correctIndex < 0 || correctIndex > 3) return null;

            return new Question(
                    "Custom",
                    etScenario.getText().toString().trim(),
                    questionText,
                    op1,
                    op2,
                    op3,
                    op4,
                    correctIndex,
                    etExplanation.getText().toString().trim()
            );
        } catch (Exception e) {
            return null;
        }
    }

    private void generateQRCode() {
        Question q = getQuestionFromFields();
        if (q == null) {
            Toast.makeText(this, "Fill question details first", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            JSONObject json = new JSONObject();
            json.put("category", q.getCategory());
            json.put("scenario", q.getScenario());
            json.put("question", q.getQuestion());
            json.put("option1", q.getOption1());
            json.put("option2", q.getOption2());
            json.put("option3", q.getOption3());
            json.put("option4", q.getOption4());
            json.put("answerIndex", q.getAnswerIndex());
            json.put("explanation", q.getExplanation());

            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(json.toString(), BarcodeFormat.QR_CODE, 400, 400);
            ivQRCode.setImageBitmap(bitmap);
            ivQRCode.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void importQuestionFromQR(String data) {
        try {
            JSONObject obj = new JSONObject(data);
            Question importedQ = new Question(
                    obj.getString("category"),
                    obj.optString("scenario", ""),
                    obj.getString("question"),
                    obj.getString("option1"),
                    obj.getString("option2"),
                    obj.getString("option3"),
                    obj.getString("option4"),
                    obj.getInt("answerIndex"),
                    obj.optString("explanation", "")
            );
            dbHelper.addQuestion(importedQ);
            Toast.makeText(this, "Quiz Imported to Database!", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Invalid Quiz QR Code", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearFields() {
        etScenario.setText("");
        etQuestion.setText("");
        etOp1.setText("");
        etOp2.setText("");
        etOp3.setText("");
        etOp4.setText("");
        etCorrectIndex.setText("");
        etExplanation.setText("");
        ivQRCode.setVisibility(View.GONE);
    }
}
