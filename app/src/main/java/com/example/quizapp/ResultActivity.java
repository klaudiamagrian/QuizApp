package com.example.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    String name;
    int score;
    int total;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView txtResult = findViewById(R.id.txtResult);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnRanking = findViewById(R.id.btnRanking);
        Button btnRetry = findViewById(R.id.btnRetry);

        // pobranie danych
        score = getIntent().getIntExtra("SCORE", 0);
        total = getIntent().getIntExtra("TOTAL", 0);
        name = getIntent().getStringExtra("NAME");
        category = getIntent().getStringExtra("CATEGORY");

        // jeśli name == null -> pokaż "Wynik"
        if (name == null || name.trim().isEmpty()) {
            name = "Wynik";
        }

        txtResult.setText(name + ": " + score + "/" + total);

        // zapis wyniku
        btnSave.setOnClickListener(v -> {
            saveScore(name, score);
            v.setEnabled(false);
        });

        // ranking
        btnRanking.setOnClickListener(v -> {
            Intent intent = new Intent(this, RankingActivity.class);
            intent.putExtra("CURRENT_NAME", name);
            intent.putExtra("CURRENT_SCORE", score);
            startActivity(intent);
        });

        // gra ponownie
        btnRetry.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            intent.putExtra("CATEGORY", category);
            intent.putExtra("NAME", name);
            startActivity(intent);
            finish();
        });
    }

    private void saveScore(String name, int score) {
        SharedPreferences prefs = getSharedPreferences("RANKING", MODE_PRIVATE);

        boolean alreadySaved = prefs.getBoolean("saved_" + name + "_" + score, false);

        if (alreadySaved) {
            Toast.makeText(this, "Ten wynik już zapisano!", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = prefs.edit();

        String old = prefs.getString("scores", "");
        String newScore = name + "|" + score;

        editor.putString("scores", old + ";" + newScore);
        editor.putBoolean("saved_" + name + "_" + score, true);

        editor.apply();

        Toast.makeText(this, "Wynik zapisany pomyślnie!", Toast.LENGTH_SHORT).show();
    }
}