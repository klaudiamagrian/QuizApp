package com.example.quizapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class RankingActivity extends AppCompatActivity {

    List<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        RecyclerView recyclerView = findViewById(R.id.recyclerRanking);

        String currentName = getIntent().getStringExtra("CURRENT_NAME");
        int currentScore = getIntent().getIntExtra("CURRENT_SCORE", -1);

        loadData();

        Collections.sort(players, (a, b) -> b.score - a.score);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RankingAdapter(players, currentName, currentScore));
    }

    private void loadData() {
        SharedPreferences prefs = getSharedPreferences("RANKING", MODE_PRIVATE);
        String data = prefs.getString("scores", "");

        if (data.isEmpty()) return;

        String[] items = data.split(";");

        for (String item : items) {
            if (item.contains("|")) {
                String[] parts = item.split("\\|");
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);
                players.add(new Player(name, score));
            }
        }
    }
}