package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        EditText editName = findViewById(R.id.editName);
        Button btnStart = findViewById(R.id.btnStart);

        String category = getIntent().getStringExtra("CATEGORY");

        btnStart.setOnClickListener(v -> {
            String name = editName.getText().toString();

            Intent intent = new Intent(this, QuizActivity.class);
            intent.putExtra("NAME", name);
            intent.putExtra("CATEGORY", category);
            startActivity(intent);
        });
    }
}