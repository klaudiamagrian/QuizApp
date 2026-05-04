package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private void startQuiz(String category) {
        Intent intent = new Intent(MainActivity.this, NameActivity.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAnimals = findViewById(R.id.btnAnimals);
        Button btnScience = findViewById(R.id.btnScience);
        Button btnSport = findViewById(R.id.btnSport);

        btnAnimals.setOnClickListener(v -> {
            startQuiz("ANIMALS");
        });

        btnScience.setOnClickListener(v -> {
            startQuiz("SCIENCE");
        });

        btnSport.setOnClickListener(v -> {
            startQuiz("SPORT");
        });
    }
}